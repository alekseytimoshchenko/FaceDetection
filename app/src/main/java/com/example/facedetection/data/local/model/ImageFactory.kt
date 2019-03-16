package com.example.facedetection.data.local.model

class ImageFactory: IImageFactory {
    override fun create(path: String): IImageObj = ImageObj(path)
}