package com.example.facedetection.ui.base

import android.support.v7.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel

interface IBaseActivity {
    fun setProgress(state: LoadingState)
}

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {
    val sharedViewModel by viewModel<SharedViewModel>()

    override fun setProgress(state: LoadingState) {
        sharedViewModel.setProgressState(state)
    }
}