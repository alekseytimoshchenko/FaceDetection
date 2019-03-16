package com.example.facedetection.utils

import android.arch.lifecycle.LiveData

fun <T> LiveData<T>.toSingleEvent(): LiveEvent<T> {
    val result = LiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}
