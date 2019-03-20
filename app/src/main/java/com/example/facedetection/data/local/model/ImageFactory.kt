package com.example.facedetection.data.local.model

class ImageFactory: IImageFactory {
    override fun create(path: String, @IImageObj.Companion.Type type: String): IImageObj = ImageObj(path, type)
}