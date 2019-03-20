package com.example.facedetection.data.local.model

interface IImageFactory {
    fun create(path: String, @IImageObj.Companion.Type type: String): IImageObj
}