package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import io.reactivex.disposables.CompositeDisposable

class GeneralPhotoScreenViewModel(
    private val repo: IGeneralRepo
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val progress = MutableLiveData<LoadingState>()

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress

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