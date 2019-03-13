package com.example.facedetection.ui.notDefinedPhotoScreen

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.not_defined_photo_screen.INotDefinedRepo

class NotDefinedPhotoScreenViewModel(
    private val repo: INotDefinedRepo
) : ViewModel(), LifecycleObserver {

}