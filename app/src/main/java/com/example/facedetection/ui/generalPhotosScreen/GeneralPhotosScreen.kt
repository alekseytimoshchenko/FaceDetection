package com.example.facedetection.ui.generalPhotosScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class GeneralPhotosScreen : BaseFragment() {

    private val model: GeneralPhotoScreenViewModel by viewModel()

    override fun getTitle(): String = "All"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_general_photos_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()
        lifecycle.addObserver(model)
    }

    private fun observeLiveData() {

    }

    companion object {
        fun newInstance(): GeneralPhotosScreen = GeneralPhotosScreen()
    }
}