package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.utils.LiveEvent
import io.reactivex.disposables.CompositeDisposable

class GeneralPhotoScreenViewModel(
    private val repo: IGeneralRepo
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val noResultContentVisibility = MutableLiveData<Boolean>()
    private val checkPermission = LiveEvent<Boolean>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        checkPermissions()
    }

    private fun checkPermissions() {
        checkPermission.postValue(true)
    }

    fun requestContent() {
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

    fun doOnTryToLoadContentClick() {
        checkPermissions()
    }

    fun noResultContainerVisibility(): LiveData<Boolean> = noResultContentVisibility

    private fun setNoResultContainerVisibility(state: Boolean) {
        noResultContentVisibility.postValue(state)
    }

    fun checkPermission(): LiveData<Boolean> = checkPermission
}