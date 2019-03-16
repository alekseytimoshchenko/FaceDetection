package com.example.facedetection.data.repo.general_photo_screen

import com.example.facedetection.utils.Constants
import io.reactivex.Single
import java.io.File

class GeneralRepo : IGeneralRepo {
    override fun allPhotos(): Single<Array<File>> =
        Single.just(File(Constants.CAMERA_FOLDER_PATH))
            .map { it.listFiles() }
            .onErrorReturn { arrayOf() }
}