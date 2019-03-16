package com.example.facedetection.ui.generalPhotosScreen

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.facedetection.R
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.ui.base.BaseFragment
import com.example.facedetection.ui.base.IAdapter
import com.example.facedetection.ui.generalPhotosScreen.adapters.GeneralPhotoAdapter
import com.example.facedetection.utils.Constants
import kotlinx.android.synthetic.main.frag_general_photos_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class GeneralPhotosScreen : BaseFragment() {

    private val model by viewModel<GeneralPhotoScreenViewModel>()

    override fun getTitle(): String = "All"

    companion object {
        fun newInstance(): GeneralPhotosScreen = GeneralPhotosScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_general_photos_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setGui()
    }

    override fun setGui() {
        rv_general_screen_all_photos.apply {
            layoutManager = GridLayoutManager(context.applicationContext, Constants.GRID_COLUMNS)
            adapter = GeneralPhotoAdapter()
        }
    }

    override fun setListeners() {
        tv_general_photos_try_load.setOnClickListener {
            model.doOnTryToLoadContentClick()
        }

        b_general_screen_detect_faces.setOnClickListener {
            model.doOnDetectFacesClick()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()
        lifecycle.addObserver(model)
    }

    private fun setNoResultContainerVisibility(state: Boolean) {
        g_genera_photos_no_result.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setContentContainerVisibility(state: Boolean) {
        g_general_screen_content.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun observeLiveData() {
        model.noResultContainerVisibility()
            .observe(viewLifecycleOwner, Observer { it?.let { setNoResultContainerVisibility(it) } })

        model.contentContainerVisibility()
            .observe(viewLifecycleOwner, Observer { it?.let { setContentContainerVisibility(it) } })

        model.checkPermission()
            .observe(viewLifecycleOwner, Observer { it?.let { checkPermission() } })

        model.getProgressState().observe(this, Observer { it?.let { setProgress(it) } })

        model.screenContent().observe(this, Observer { it?.let { setScreenContent(it) } })

        model.showEmptyFolderToast().observe(this, Observer { it?.let { showEmptyFolderToast() } })
    }

    private fun setScreenContent(content: List<IImageObj>) {
        (rv_general_screen_all_photos.adapter as? IAdapter<List<IImageObj>>)?.setContent(content)
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            model.requestContent()
        } else {
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                Constants.REQUEST_READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun showEmptyFolderToast() {
        Toast.makeText(context?.applicationContext, getString(R.string.empty_folder), Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.REQUEST_READ_EXTERNAL_STORAGE -> {
                grantResults.forEach {
                    if (it != PackageManager.PERMISSION_GRANTED) {
                        model.permissionDenied()
                        return
                    }
                }

                model.requestContent()
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}