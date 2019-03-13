package com.example.facedetection.ui.base

import android.support.v7.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel

interface IBaseActivity {
    fun setProgress(state: LoadingState)
}

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {
    val viewModel by viewModel<SharedViewModel>()

    override fun setProgress(state: LoadingState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}