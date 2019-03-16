package com.example.facedetection.ui.generalPhotosScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.local.model.IImageFactory
import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import com.example.facedetection.utils.LiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class GeneralPhotoScreenViewModel(
    private val repo: IGeneralRepo,
    private val WORKER_SCHEDULER: Scheduler,
    private val imageFactory: IImageFactory
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val noResultContentVisibility = MutableLiveData<Boolean>()
    private val checkPermission = LiveEvent<Boolean>()
    private val progress = LiveEvent<LoadingState>()
    private val screenContent = MutableLiveData<List<ImageObj>>()

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        checkPermissions()
    }

    private fun checkPermissions() {
        checkPermission.postValue(true)
    }

    fun requestContent() {
        disposable.add(
            repo.allPhotos()
                .map { it.toList() }
                .toObservable()
                .flatMapIterable { it }
                .filter { it.name.contains(".jpg") || it.name.contains(".png") }
                .map { it.absolutePath }
                .map { imageFactory.create(it) }
                .toList()
                .doOnSubscribe { setProgressState(LoadingState.LOADING) }
                .doOnError { setProgressState(LoadingState.ERROR) }
                .doFinally { setProgressState(LoadingState.SUCCESS) }
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            setNoResultContainerVisibility(true)
                        } else {
                            setContent(it)
                            setNoResultContainerVisibility(false)
                        }
                    },
                    { Timber.e(it) }
                )
        )
    }

    private fun setContent(content: List<ImageObj>) {
        screenContent.postValue(content)
    }

    fun screenContent(): LiveData<List<ImageObj>> = screenContent

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

    //        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView)

}