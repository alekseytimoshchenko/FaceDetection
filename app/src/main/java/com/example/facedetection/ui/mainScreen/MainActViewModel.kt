package com.example.facedetection.ui.mainScreen

import android.arch.lifecycle.*
import com.example.facedetection.data.repo.main_act.IMainRepo
import com.example.facedetection.ui.base.IBaseFragment
import com.example.facedetection.ui.base.IBaseViewModel
import com.example.facedetection.ui.base.LoadingState
import com.example.facedetection.utils.LiveEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


/**
 * Created with care by Alexey.T on 12/03/2019.
 */
class MainActViewModel(
    private val repo: IMainRepo,
    private val WORKER_SCHEDULER: Scheduler
) : ViewModel(), IBaseViewModel, LifecycleObserver {

    private val disposable = CompositeDisposable()

    private val progress = LiveEvent<LoadingState>()
    private val faceCount = LiveEvent<Int>()
    private val content = MutableLiveData<List<IBaseFragment>>()

    init {
        requestContent()
        getDetectedFaceCount()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    private fun getDetectedFaceCount() {
        disposable.add(
            repo.getDetectedFaceCount()
                .filter { it > 0 }
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    { setFaceCount(it) },
                    { Timber.e(it) }
                )
        )
    }

    override fun setProgressState(state: LoadingState) {
        progress.postValue(state)
    }

    override fun getProgressState(): LiveData<LoadingState> = progress

    private fun requestContent() {
        disposable.add(
            repo.getTabs()
                .doOnSubscribe { setProgressState(LoadingState.LOADING) }
                .doOnError { setProgressState(LoadingState.ERROR) }
                .doFinally { setProgressState(LoadingState.SUCCESS) }
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    { postContent(it) },
                    { Timber.e(it) }
                )
        )
    }

    fun contentLD() = content

    fun faceCount() = faceCount

    private fun setFaceCount(count: Int) {
        faceCount.postValue(count)
    }

    private fun postContent(content: List<IBaseFragment>) {
        this.content.postValue(content)
    }

    override fun onCleared() {
        super.onCleared()

        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}