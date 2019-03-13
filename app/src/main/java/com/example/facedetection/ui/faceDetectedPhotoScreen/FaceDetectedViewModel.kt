package com.example.facedetection.ui.faceDetectedPhotoScreen

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.face_detected_photo_screen.IFaceDetectedRepo

class FaceDetectedViewModel(
    private val repo: IFaceDetectedRepo
): ViewModel(), LifecycleObserver