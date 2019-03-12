package com.example.facedetection.di

import com.example.facedetection.data.repo.main_act.FragmentFactory
import com.example.facedetection.data.repo.main_act.IMainRepo
import com.example.facedetection.data.repo.main_act.MainRepo
import com.example.facedetection.ui.mainScreen.MainActViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    factory { FragmentFactory() }

    single { Schedulers.io() }

    single<IMainRepo> { MainRepo(get()) }

    viewModel { MainActViewModel(get(), get()) }
}