package com.example.facedetection.ui.mainScreen

import android.os.Bundle
import com.example.facedetection.R
import com.example.facedetection.ui.adapters.SectionsPagerAdapter
import com.example.facedetection.ui.base.BaseActivity
import com.example.facedetection.ui.faceDetectedPhotoScreen.FaceDetectedPhotoScreen
import com.example.facedetection.ui.generalPhotosScreen.GeneralPhotosScreen
import com.example.facedetection.ui.notDefinedPhotoScreen.NotDefinedPhotoScreen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setGui()
    }

    private fun setGui() {
        vp_main_act.adapter = SectionsPagerAdapter(supportFragmentManager)

        val list = listOf(//
            GeneralPhotosScreen.newInstance(), //
            FaceDetectedPhotoScreen.newInstance(), //
            NotDefinedPhotoScreen.newInstance()//
        )

        (vp_main_act.adapter as? SectionsPagerAdapter)?.setContent(list)

        tl_main_act.setupWithViewPager(vp_main_act)
    }
}
