package com.example.facedetection.ui.notDefinedPhotoScreen

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.not_defined_photo_screen.INotDefinedRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState

class NotDefinedPhotoScreenViewModel(
    private val repo: INotDefinedRepo
) : ViewModel(), IBaseViewModel, LifecycleObserver {
    private val progress = MutableLiveData<LoadingState>()

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress
}