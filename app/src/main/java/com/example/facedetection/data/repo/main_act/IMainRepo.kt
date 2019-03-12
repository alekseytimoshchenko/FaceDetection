package com.example.facedetection.data.repo.main_act

import com.example.facedetection.ui.base.BaseFragment
import io.reactivex.Single

interface IMainRepo {
    fun getContent(): Single<List<BaseFragment>>
}