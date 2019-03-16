package com.example.facedetection.data.local.model

interface IImageFactory {
    fun create(path: String): ImageObj
}