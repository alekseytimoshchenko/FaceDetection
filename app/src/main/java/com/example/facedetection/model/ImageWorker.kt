package com.example.facedetection.model

import android.graphics.Bitmap
import android.os.Environment
import androidx.work.Worker
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.facedetection.App
import com.example.facedetection.data.local.model.IImageFactory
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.local.model.ImageFactory
import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.utils.Constants
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.FaceDetector
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.io.File

class ImageWorker : Worker() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    private fun getBitmap(path: String): Bitmap = Glide.with(applicationContext)
        .asBitmap()
        .load(path)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(200, 200)
        .get()

    override fun doWork(): WorkerResult {

        val detector = FaceDetector.Builder(applicationContext)
            .setTrackingEnabled(false)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .build()

        disposable.add(
            getImageObjsList(ImageFactory())
                .toObservable()
                .flatMapIterable { it }
                .cast(ImageObj::class.java)
                .flatMap(
                    {
                        val path = it.getImagePath()
                        val bitmap = getBitmap(path)
                        val frame = Frame.Builder().setBitmap(bitmap).build()
                        val faces = detector.detect(frame)
                        Observable.just(faces.size() > 0)
                    },
                    { iImageObj, iIsFaceDetected ->
                        iImageObj.type = if (iIsFaceDetected) IImageObj.DETECTED else IImageObj.NOT_DETECTED
                        iImageObj
                    }
                )
                .toList()
                .subscribe(
                    { App.instance.imageDb.imageDao().insertImages(it) },
                    { Timber.e(it) }
                )
        )

        return Worker.WorkerResult.SUCCESS
    }

    private fun getCameraPath(): Single<String> {
        return Single.just(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))
            .map { it.toString() }
            .map { it + Constants.CAMERA_APPENDIX }
    }

    private fun allPhotos(): Single<Array<File>> =
        getCameraPath().map { File(it) }
            .map { it.listFiles() }
            .onErrorReturn { arrayOf() }

    private fun getImageObjsList(imageFactory: IImageFactory): Single<MutableList<IImageObj>> {
        return allPhotos().map { it.toList() }
            .toObservable()
            .flatMapIterable { it }
            .filter { it.name.contains(Constants.JPG) || it.name.contains(Constants.PNG) }
            .map { it.absolutePath }
            .map { imageFactory.create(it, IImageObj.NOT_DETECTED) }
            .toList()
    }

    override fun onStopped() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }

        super.onStopped()
    }
}