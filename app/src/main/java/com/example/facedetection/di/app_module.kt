package com.example.facedetection.di

import com.example.facedetection.App
import com.example.facedetection.data.local.model.IImageFactory
import com.example.facedetection.data.local.model.ImageFactory
import com.example.facedetection.data.repo.face_detected_photo_screen.FaceDetectedRepo
import com.example.facedetection.data.repo.face_detected_photo_screen.IFaceDetectedRepo
import com.example.facedetection.data.repo.general_photo_screen.GeneralRepo
import com.example.facedetection.data.repo.general_photo_screen.IGeneralRepo
import com.example.facedetection.data.repo.main_act.FragmentFactory
import com.example.facedetection.data.repo.main_act.IMainRepo
import com.example.facedetection.data.repo.main_act.MainRepo
import com.example.facedetection.data.repo.not_defined_photo_screen.INotDefinedRepo
import com.example.facedetection.data.repo.not_defined_photo_screen.NotDefinedRepo
import com.example.facedetection.ui.base.SharedViewModel
import com.example.facedetection.ui.faceDetectedPhotoScreen.FaceDetectedViewModel
import com.example.facedetection.ui.generalPhotosScreen.GeneralPhotoScreenViewModel
import com.example.facedetection.ui.mainScreen.MainActViewModel
import com.example.facedetection.ui.notDefinedPhotoScreen.NotDefinedPhotoScreenViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory

val mainModule = module {
    factory<FragmentFactory>()

    single { Schedulers.io() }

    single<IMainRepo> { MainRepo(get()) }

    viewModel<MainActViewModel>()
}

val generalScreenModule = module {

    factory<IImageFactory> { ImageFactory() }

    factory { App.instance }

    factory { App.instance.imageDb.imageDao() }

    single<IGeneralRepo> { GeneralRepo() }

    viewModel<GeneralPhotoScreenViewModel>()
}

val notDefinedScreenModule = module {
    single<INotDefinedRepo> { NotDefinedRepo() }

    viewModel<NotDefinedPhotoScreenViewModel>()
}

val faceDetectedModule = module {
    single<IFaceDetectedRepo> { FaceDetectedRepo() }

    viewModel<FaceDetectedViewModel>()
}

val sharedModule = module {
    viewModel<SharedViewModel>()
}