package com.example.facedetection.data.local.model

import com.example.facedetection.utils.diff.IDiff

interface IImageObj : IDiff<IImageObj>{
    fun getImagePath():String
}