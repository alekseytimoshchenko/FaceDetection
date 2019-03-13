package com.example.facedetection.data.repo.main_act

import com.example.facedetection.ui.base.IBaseFragment
import io.reactivex.Single

interface IMainRepo {
    fun getContent(): Single<List<IBaseFragment>>
}