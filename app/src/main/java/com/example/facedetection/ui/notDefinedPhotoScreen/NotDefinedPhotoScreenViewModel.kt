package com.example.facedetection.ui.notDefinedPhotoScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.repo.not_defined_photo_screen.INotDefinedRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import com.example.facedetection.utils.LiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class NotDefinedPhotoScreenViewModel(
    private val repo: INotDefinedRepo,
    private val WORKER_SCHEDULER: Scheduler
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val screenContent = MutableLiveData<List<IImageObj>>()
    private val noResultContentVisibility = MutableLiveData<Boolean>()
    private val contentContainerVisibility = MutableLiveData<Boolean>()

    private val progress = LiveEvent<LoadingState>()

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        requestContent()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    private fun requestContent() {
        disposable.add(
            repo.getDBPhotoImages()
                .doOnSubscribe { setProgressState(LoadingState.LOADING) }
                .doOnError { setProgressState(LoadingState.ERROR) }
                .doOnComplete { setProgressState(LoadingState.SUCCESS) }
                .doOnCancel { setProgressState(LoadingState.SUCCESS) }
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            setNoResultContainerVisibility(true)
                            setContentContainerVisibility(false)
                        } else {
                            setContent(it)
                            setNoResultContainerVisibility(false)
                            setContentContainerVisibility(true)
                        }

                        setProgressState(LoadingState.SUCCESS)
                    },
                    { Timber.e(it) }
                )
        )
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