package com.example.facedetection.ui.generalPhotosScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.ui.base.BaseFragment

class GeneralPhotosScreen : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_general_photos_layout, container, false)


    companion object {
        fun newInstance(): GeneralPhotosScreen = GeneralPhotosScreen()
    }
}