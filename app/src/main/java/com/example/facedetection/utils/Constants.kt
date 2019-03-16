package com.example.facedetection.utils

import android.os.Environment

class Constants {
    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 123
        val CAMERA_FOLDER_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera"
    }
}