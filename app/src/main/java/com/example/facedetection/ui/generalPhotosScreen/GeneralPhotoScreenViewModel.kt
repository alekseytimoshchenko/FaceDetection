package com.example.facedetection.ui.generalPhotosScreen

import android.app.Application
import android.arch.lifecycle.*
import com.example.facedetection.App
import com.example.facedetection.R
import com.example.facedetection.data.local.model.IImageFactory
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import com.example.facedetection.utils.LiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class GeneralPhotoScreenViewModel(
    private val app: Application,
    private val repo: IGeneralRepo,
    private val WORKER_SCHEDULER: Scheduler,
    private val imageFactory: IImageFactory
) : AndroidViewModel(app), IBaseViewModel, LifecycleObserver {

    private val noResultContentVisibility = MutableLiveData<Boolean>()
    private val contentContainerVisibility = MutableLiveData<Boolean>()
    private val checkPermission = LiveEvent<Boolean>()
    private val progress = LiveEvent<LoadingState>()
    private val showToast = LiveEvent<String>()
    private val screenContent = MutableLiveData<List<IImageObj>>()

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
                            setContentContainerVisibility(false)
                            setNoResultContainerVisibility(true)
                            doShowToast(getApplication<App>().getString(R.string.empty_folder))
                        } else {
                            setNoResultContainerVisibility(false)
                            setContentContainerVisibility(true)
                            setContent(it)
                            doShowToast(String.format("%s %s", getApplication<App>().getString(R.string.photo_num), it.size))
                        }
                    },
                    { Timber.e(it) }
                )
        )
    }

    fun showToast() = showToast

    private fun doShowToast(message: String) {
        showToast.postValue(message)
    }

    private fun setContent(content: List<IImageObj>) {
        screenContent.postValue(content)
    }

    fun screenContent(): LiveData<List<IImageObj>> = screenContent

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

    fun contentContainerVisibility(): LiveData<Boolean> = contentContainerVisibility

    private fun setNoResultContainerVisibility(state: Boolean) {
        noResultContentVisibility.postValue(state)
    }

    private fun setContentContainerVisibility(state: Boolean) {
        contentContainerVisibility.postValue(state)
    }

    fun doOnDetectFacesClick() {

    }

    fun permissionDenied() {
        setContentContainerVisibility(false)
        setNoResultContainerVisibility(true)
    }

    fun checkPermission(): LiveData<Boolean> = checkPermission
}