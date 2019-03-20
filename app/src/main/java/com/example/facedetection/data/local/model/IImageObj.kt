package com.example.facedetection.data.local.model

import android.support.annotation.StringDef
import com.example.facedetection.utils.diff.IDiff

interface IImageObj : IDiff<IImageObj> {
    companion object {
        @StringDef(DETECTED, NOT_DETECTED)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Type

        const val DETECTED = "detected"
        const val NOT_DETECTED = "not_detected"
    }

    fun getImagePath(): String

    @Type
    fun getImageType(): String
}