package com.example.facedetection.ui.mainScreen

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Environment
import com.example.facedetection.R
import com.example.facedetection.ui.adapters.SectionsPagerAdapter
import com.example.facedetection.ui.base.BaseActivity
import com.example.facedetection.ui.base.IBaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.File

class MainActivity : BaseActivity() {

    private val model by viewModel<MainActViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setGui()
        observeLiveData()
        lifecycle.addObserver(model)
    }

    private fun testDeleteIt() {
        val photoList = mutableListOf<String>()

        val cameraFolder = "Camera"

        val completeCameraFolder =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + cameraFolder

        val DCMI_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()

        val cameraDirectory = File(completeCameraFolder)
        val files = cameraDirectory.listFiles()

        for (tmp in files) {
            if (tmp.name.contains(".jpg") || tmp.name.contains(".png")) {
//                photoList.add(tmp.name)
                photoList.add(tmp.absolutePath)
            }
        }

        Timber.e("HERE")

//        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView)
//        Glide.with(this).load(photoList[0]).into(iv_test_delete_it)

    }

    private fun observeLiveData() {
        model.contentLD().observe(this, Observer { setContent(it) })
    }

    private fun setContent(it: List<IBaseFragment>?) {
        (vp_main_act.adapter as SectionsPagerAdapter).setContent(it!!)
    }

    private fun setGui() {
        vp_main_act.adapter = SectionsPagerAdapter(supportFragmentManager)
        tl_main_act.setupWithViewPager(vp_main_act)
    }
}
