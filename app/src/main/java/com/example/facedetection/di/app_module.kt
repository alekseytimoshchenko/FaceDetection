package com.example.facedetection.di

import com.example.facedetection.data.repo.general_photo_screen.GeneralRepo
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.data.repo.main_act.FragmentFactory
import com.example.facedetection.data.repo.main_act.IMainRepo
import com.example.facedetection.data.repo.main_act.MainRepo
import com.example.facedetection.data.repo.not_defined_photo_screen.INotDefinedRepo
import com.example.facedetection.data.repo.not_defined_photo_screen.NotDefinedRepo
import com.example.facedetection.ui.generalPhotosScreen.GeneralPhotoScreenViewModel
import com.example.facedetection.ui.mainScreen.MainActViewModel
import com.example.facedetection.ui.notDefinedPhotoScreen.NotDefinedPhotoScreenViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    factory { FragmentFactory() }

    single { Schedulers.io() }

    single<IMainRepo> { MainRepo(get()) }

    viewModel { MainActViewModel(get(), get()) }
}

val generalScreenModule = module {

    single<IGeneralRepo> { GeneralRepo() }

    viewModel { GeneralPhotoScreenViewModel(get()) }
}

val notDefinedScreenModule = module {
    single<INotDefinedRepo> { NotDefinedRepo() }

    viewModel { NotDefinedPhotoScreenViewModel(get()) }
}