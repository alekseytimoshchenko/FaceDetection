package com.example.facedetection.ui.mainScreen

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.facedetection.R
import com.example.facedetection.ui.adapters.SectionsPagerAdapter
import com.example.facedetection.ui.base.BaseActivity
import com.example.facedetection.ui.base.IBaseFragment
import com.example.facedetection.ui.base.LoadingState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val model by viewModel<MainActViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setGui()
        observeLiveData()
        lifecycle.addObserver(model)
    }

    private fun observeLiveData() {
        model.contentLD().observe(this, Observer { setContent(it) })
        model.getProgressState().observe(this, Observer { it?.let { setProgress(it) } })
        model.faceCount().observe(this, Observer { it?.let { showSnackBar(it) } })
        sharedViewModel.getProgressState().observe(this, Observer { it?.let { showProgress(it) } })
    }

    private fun showSnackBar(it: Int) {
        Snackbar.make(findViewById(R.id.cl_main_act), "Detect $it photos with face", Snackbar.LENGTH_LONG).show()
    }

    private fun showProgress(state: LoadingState) {
        when (state) {
            LoadingState.LOADING -> {
                setLoadingVisibility(true)
            }
            LoadingState.SUCCESS -> {
                setLoadingVisibility(false)
            }
            LoadingState.ERROR -> {
                setLoadingVisibility(false)
            }
        }
    }

    private fun setLoadingVisibility(state: Boolean) {
        pb_main_act.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setContent(it: List<IBaseFragment>?) {
        (vp_main_act.adapter as SectionsPagerAdapter).setContent(it!!)
    }

    private fun setGui() {
        vp_main_act.adapter = SectionsPagerAdapter(supportFragmentManager)
        tl_main_act.setupWithViewPager(vp_main_act)
    }
}
