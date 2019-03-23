package com.example.facedetection.data.repo.not_defined_photo_screen

import com.example.facedetection.data.local.db.ImageDao
import com.example.facedetection.data.local.model.ImageObj
import io.reactivex.Flowable

class NotDefinedRepo(private val db: ImageDao): INotDefinedRepo {
    override fun getDBPhotoImages(): Flowable<List<ImageObj>> = db.getAllImages()
}