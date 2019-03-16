package com.example.facedetection.data.repo.general_photo_screen

import com.example.facedetection.data.repo.base.IBaseRepo
import io.reactivex.Single
import java.io.File

interface IGeneralRepo : IBaseRepo {
    fun allPhotos(): Single<Array<File>>
}