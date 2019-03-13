package com.example.facedetection.data.repo.main_act

import com.example.facedetection.data.repo.base.IBaseRepo
import com.example.facedetection.ui.base.IBaseFragment
import io.reactivex.Single

interface IMainRepo : IBaseRepo {
    fun getContent(): Single<List<IBaseFragment>>
}