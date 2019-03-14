package com.example.facedetection.ui.base

import android.arch.lifecycle.LiveData
import android.support.annotation.MainThread

/**
 * Created with care by Alexey.T on 14/03/2019.
 */

interface IProgress {
    fun getProgressState(): LiveData<LoadingState>

    @MainThread
    fun setProgressState(state: LoadingState)
}