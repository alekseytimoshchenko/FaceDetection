package com.example.facedetection.utils

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.facedetection.model.ImageWorker
import io.reactivex.Single
import java.util.*


/**
 * Class that define if there is a face on the image or not
 * */
interface IImageProcessor {
    fun startFaceDetectProcess(): Single<UUID>
}

class ImageProcessor : IImageProcessor {

    override fun startFaceDetectProcess(): Single<UUID> {
        val imageFaceDetectRequest = OneTimeWorkRequest.Builder(ImageWorker::class.java)
            .build()

        WorkManager.getInstance().enqueue(imageFaceDetectRequest)

        return Single.just(imageFaceDetectRequest.id)
    }
}