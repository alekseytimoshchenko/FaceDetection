package com.example.facedetection.data.repo.not_defined_photo_screen

import com.example.facedetection.data.local.db.ImageDao
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.data.repo.base.IBaseRepo
import io.reactivex.Flowable

interface INotDefinedRepo: IBaseRepo {
    fun getNotDefinedPhotoImages() : Flowable<List<ImageObj>>
}

class NotDefinedRepo(private val db: ImageDao): INotDefinedRepo {
    override fun getNotDefinedPhotoImages() = db.getImagesBy(IImageObj.NOT_DETECTED)
}