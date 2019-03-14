package com.example.facedetection.ui.faceDetectedPhotoScreen

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.face_detected_photo_screen.IFaceDetectedRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState

class FaceDetectedViewModel(
    private val repo: IFaceDetectedRepo
) : ViewModel(), IBaseViewModel, LifecycleObserver {
    private val progress = MutableLiveData<LoadingState>()

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress
}