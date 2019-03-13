package com.example.facedetection.ui.base

import android.support.v4.app.Fragment

interface IBaseFragment {
    fun getTitle(): String
}

abstract class BaseFragment : Fragment(), IBaseFragment {
    abstract override fun getTitle(): String
}