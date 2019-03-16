package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.utils.Constants
import com.example.facedetection.utils.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.io.File

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
//        disposable.add(
//            repo.allPhotos()
//        )
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

    private fun testDeleteIt() {
        val photoList = mutableListOf<String>()

        val files = File(Constants.CAMERA_FOLDER_PATH).listFiles()

        for (tmp in files) {
            if (tmp.name.contains(".jpg") || tmp.name.contains(".png")) {
                photoList.add(tmp.absolutePath)
            }
        }

        Timber.e("HERE")

//        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView)
//        Glide.with(this).load(photoList[0]).into(iv_test_delete_it)
    }
}