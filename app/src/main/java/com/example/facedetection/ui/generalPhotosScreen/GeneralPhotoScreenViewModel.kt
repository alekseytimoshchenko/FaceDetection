package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import io.reactivex.disposables.CompositeDisposable

class GeneralPhotoScreenViewModel(
    private val repo: IGeneralRepo
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        checkPermissions()
    }

    private fun checkPermissions() {
        requestContent()
    }

    private fun requestContent() {
        disposable
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    override fun onCleared() {
        super.onCleared()

        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}