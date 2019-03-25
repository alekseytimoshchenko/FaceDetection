package com.example.facedetection.data.repo.main_act

import com.example.facedetection.data.local.db.ImageDao
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.data.repo.base.IBaseRepo
import com.example.facedetection.ui.base.IBaseFragment
import com.example.facedetection.ui.faceDetectedPhotoScreen.FaceDetectedPhotoScreen
import com.example.facedetection.ui.generalPhotosScreen.GeneralPhotosScreen
import com.example.facedetection.ui.notDefinedPhotoScreen.NotDefinedPhotoScreen
import io.reactivex.Flowable
import io.reactivex.Single

interface IMainRepo : IBaseRepo {
    fun getTabs(): Single<List<IBaseFragment>>

    fun getDetectedFaceCount(): Flowable<Int>
}

class MainRepo(private val fragFactory: FragmentFactory,
               private val db: ImageDao) : IMainRepo {
    override fun getDetectedFaceCount() = db.getImagesCountBy(IImageObj.DETECTED)

    override fun getTabs(): Single<List<IBaseFragment>> = Single.just(
        listOf(
            fragFactory.create(FragmentFactory.Types.GeneralScreen),
            fragFactory.create(FragmentFactory.Types.FaceDetectedScreen),
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
            Types.GeneralScreen -> GeneralPhotosScreen.newInstance()
            Types.FaceDetectedScreen -> FaceDetectedPhotoScreen.newInstance()
            Types.NotDefinedScreen -> NotDefinedPhotoScreen.newInstance()
        }
    }
}