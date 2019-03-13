package com.example.facedetection.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.facedetection.utils.toSingleEvent

class SharedViewModel: ViewModel() {
    private val progressLD = MutableLiveData<LoadingState>()

    val progress = progressLD.toSingleEvent()

    fun setProgress(state: LoadingState) {
        progressLD.value = state
    }
}