package com.example.facedetection.ui.base

import android.arch.lifecycle.LiveData

/**
 * Created with care by Alexey.T on 14/03/2019.
 */
interface ISharedVM{
    fun getProgressState(): LiveData<LoadingState>

    fun setProgressState(state: LoadingState)
}