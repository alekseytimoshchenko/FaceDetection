package com.example.facedetection.ui.mainScreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.facedetection.data.repo.main_act.IMainRepo
import com.example.facedetection.ui.base.BaseFragment
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created with care by Alexey.T on 12/03/2019.
 */
class MainActViewModel(
    private val repo: IMainRepo,
    private val WORKER_SCHEDULER: Scheduler
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    private val content: MutableLiveData<List<BaseFragment>> = MutableLiveData()

    fun requestContent() {
        disposable.add(
            repo.getContent()
                .subscribeOn(WORKER_SCHEDULER)
                .subscribe(
                    { postContent(it) },
                    { Timber.e(it) }
                )
        )
    }

     fun contentLD(): LiveData<List<BaseFragment>> = content

    private fun postContent(content: List<BaseFragment>) {
        this.content.postValue(content)
    }

    override fun onCleared() {
        super.onCleared()

        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}