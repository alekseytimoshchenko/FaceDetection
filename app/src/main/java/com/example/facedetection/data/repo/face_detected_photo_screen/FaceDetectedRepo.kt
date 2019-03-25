package com.example.facedetection.data.repo.face_detected_photo_screen

import com.example.facedetection.data.local.db.ImageDao
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.data.repo.base.IBaseRepo
import io.reactivex.Flowable

interface IFaceDetectedRepo : IBaseRepo {
    fun getFaceDetectedPhotoImages(): Flowable<List<ImageObj>>
}

class FaceDetectedRepo(private val db: ImageDao) : IFaceDetectedRepo {
    override fun getFaceDetectedPhotoImages() = db.getImagesBy(IImageObj.DETECTED)
}