package com.example.facedetection.ui.base

import android.support.v4.app.Fragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

interface IBaseFragment {
    fun getTitle(): String

    fun setProgress(state: LoadingState)
}

abstract class BaseFragment : Fragment(), IBaseFragment {
    private val viewModel by sharedViewModel<SharedViewModel>()

    abstract override fun getTitle(): String

    override fun setProgress(state: LoadingState) {
        viewModel.setProgress(state)
    }
}