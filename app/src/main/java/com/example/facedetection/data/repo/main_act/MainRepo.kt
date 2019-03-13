package com.example.facedetection.data.repo.main_act

import com.example.facedetection.ui.base.IBaseFragment
import com.example.facedetection.ui.faceDetectedPhotoScreen.FaceDetectedPhotoScreen
import com.example.facedetection.ui.generalPhotosScreen.GeneralPhotosScreen
import com.example.facedetection.ui.notDefinedPhotoScreen.NotDefinedPhotoScreen
import io.reactivex.Single

class MainRepo(private val fragFactory: FragmentFactory) : IMainRepo {
    override fun getContent(): Single<List<IBaseFragment>> = Single.just(
        listOf(
            fragFactory.create(FragmentFactory.Types.FaceDetectedScreen),
            fragFactory.create(FragmentFactory.Types.GeneralScreen),
            fragFactory.create(FragmentFactory.Types.NotDefinedScreen)
        )
    )
}

class FragmentFactory {
    enum class Types {
        FaceDetectedScreen, GeneralScreen, NotDefinedScreen
    }

    fun create(type: Types): IBaseFragment {
        return when (type) {
            Types.FaceDetectedScreen -> FaceDetectedPhotoScreen.newInstance()
            Types.GeneralScreen -> GeneralPhotosScreen.newInstance()
            Types.NotDefinedScreen -> NotDefinedPhotoScreen.newInstance()
        }
    }
}