package com.example.facedetection.data.repo.general_photo_screen

import android.os.Environment
import com.example.facedetection.data.local.db.ImageDao
import com.example.facedetection.utils.Constants
import io.reactivex.Single
import java.io.File

class GeneralRepo(private val db: ImageDao) : IGeneralRepo {
    override fun nukeAllImages() {
        db.nukeAllImages()
    }

    override fun getCameraPath(): Single<String> {
        return Single.just(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))
            .map { it.toString() }
            .map { it + Constants.CAMERA_APPENDIX }
    }

    override fun allPhotos(): Single<Array<File>> =
        getCameraPath().map { File(it) }
            .map { it.listFiles() }
            .onErrorReturn { arrayOf() }
}