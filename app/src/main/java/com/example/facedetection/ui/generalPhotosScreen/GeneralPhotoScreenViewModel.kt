package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo

class GeneralPhotoScreenViewModel(
    private val repo: IGeneralRepo
) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }
}