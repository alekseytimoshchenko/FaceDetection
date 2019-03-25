package com.example.facedetection.ui.faceDetectedPhotoScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.repo.face_detected_photo_screen.IFaceDetectedRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import com.example.facedetection.utils.LiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class FaceDetectedViewModel(
    private val repo: IFaceDetectedRepo,
    private val WORKER_SCHEDULER: Scheduler
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val screenContent = MutableLiveData<List<IImageObj>>()
    private val noResultContentVisibility = MutableLiveData<Boolean>()
    private val contentContainerVisibility = MutableLiveData<Boolean>()

    private val progress = LiveEvent<LoadingState>()

    private val disposable: CompositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    init {
        requestContent()
    }

    private fun requestContent() {
        disposable.add(
            repo.getFaceDetectedPhotoImages()
                .doOnSubscribe { isResultVisible(false) }
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            isResultVisible(false)
                        } else {
                            setContent(it)
                            isResultVisible(true)
                        }
                    },
                    { Timber.e(it) }
                )
        )
    }

    private fun isResultVisible(state: Boolean) {
        setNoResultContainerVisibility(!state)
        setContentContainerVisibility(state)
    }

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress

    private fun setContent(content: List<IImageObj>) {
        screenContent.postValue(content)
    }

    fun screenContent(): LiveData<List<IImageObj>> = screenContent

    fun noResultContainerVisibility(): LiveData<Boolean> = noResultContentVisibility

    private fun setNoResultContainerVisibility(state: Boolean) {
        noResultContentVisibility.postValue(state)
    }

    fun contentContainerVisibility(): LiveData<Boolean> = contentContainerVisibility

    private fun setContentContainerVisibility(state: Boolean) {
        contentContainerVisibility.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()

        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}