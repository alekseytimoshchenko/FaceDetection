package com.example.facedetection.ui.notDefinedPhotoScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.ui.base.BaseFragment

class NotDefinedPhotoScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_not_defined_photo_layout, container, false)

    companion object {
        // private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(): NotDefinedPhotoScreen {
            val fragment = NotDefinedPhotoScreen()
            val args = Bundle()
            // args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}