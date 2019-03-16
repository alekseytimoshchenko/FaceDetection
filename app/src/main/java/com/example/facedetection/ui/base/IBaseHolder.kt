package com.example.facedetection.ui.base

interface IBaseHolder<TYPE> {
    fun onBind(item: TYPE)
}