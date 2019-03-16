package com.example.facedetection.data.local.model

data class ImageObj(val path: String) : IImageObj {
    override fun getImagePath(): String = path

    override fun areItemsTheSame(obj: IImageObj): Boolean =
        this.getImagePath() == obj.getImagePath()

    override fun areContentsTheSame(obj: IImageObj): Boolean =
        this.getImagePath() == obj.getImagePath()
}