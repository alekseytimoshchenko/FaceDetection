package com.example.facedetection.data.repo.not_defined_photo_screen

import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.data.repo.base.IBaseRepo
import io.reactivex.Flowable

interface INotDefinedRepo: IBaseRepo {
    fun getDBPhotoImages() : Flowable<List<ImageObj>>
}