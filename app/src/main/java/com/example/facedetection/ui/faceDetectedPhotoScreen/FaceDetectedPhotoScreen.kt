package com.example.facedetection.ui.faceDetectedPhotoScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.ui.base.BaseFragment

class FaceDetectedPhotoScreen : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_face_detected_photo_layout, container, false)

    companion object {
        fun newInstance(): FaceDetectedPhotoScreen = FaceDetectedPhotoScreen()
    }
}