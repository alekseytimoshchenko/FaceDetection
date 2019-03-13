package com.example.facedetection.ui.mainScreen

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.facedetection.R
import com.example.facedetection.ui.adapters.SectionsPagerAdapter
import com.example.facedetection.ui.base.BaseActivity
import com.example.facedetection.ui.base.IBaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val model: MainActViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setGui()
        model.requestContent()
        observeLiveData()
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
