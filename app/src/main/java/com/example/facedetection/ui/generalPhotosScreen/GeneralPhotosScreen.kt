package com.example.facedetection.ui.generalPhotosScreen

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.ui.base.BaseFragment
import com.example.facedetection.utils.Constants
import kotlinx.android.synthetic.main.frag_general_photos_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class GeneralPhotosScreen : BaseFragment() {

    private val model: GeneralPhotoScreenViewModel by viewModel()

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
    }

    private fun setListeners() {
        tv_general_photos_try_load.setOnClickListener {
            model.doOnTryToLoadContentClick()
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

    private fun observeLiveData() {
        model.noResultContainerVisibility()
            .observe(viewLifecycleOwner, Observer { it?.let { setNoResultContainerVisibility(it) } })

        model.checkPermission()
            .observe(viewLifecycleOwner, Observer { it?.let { checkPermission() } })
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.REQUEST_READ_EXTERNAL_STORAGE -> {
                grantResults.forEach {
                    if (it != PackageManager.PERMISSION_GRANTED) {
                        return
                    }
                }

                model.requestContent()
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}