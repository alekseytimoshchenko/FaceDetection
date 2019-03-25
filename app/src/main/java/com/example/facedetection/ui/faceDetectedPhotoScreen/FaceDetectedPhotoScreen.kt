package com.example.facedetection.ui.faceDetectedPhotoScreen

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facedetection.R
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.ui.base.BaseFragment
import com.example.facedetection.ui.base.IAdapter
import com.example.facedetection.ui.faceDetectedPhotoScreen.adapters.FaceDetectedAdapter
import com.example.facedetection.utils.Constants
import kotlinx.android.synthetic.main.frag_face_detected_photo_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class FaceDetectedPhotoScreen : BaseFragment() {

    private val model by viewModel<FaceDetectedViewModel>()

    override fun getTitle(): String = "Faces"

    companion object {
        fun newInstance(): FaceDetectedPhotoScreen = FaceDetectedPhotoScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frag_face_detected_photo_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setGui()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()
        lifecycle.addObserver(model)
    }

    private fun setNoResultContainerVisibility(state: Boolean) {
        iv_detected_photo_empty_folder.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setContentContainerVisibility(state: Boolean) {
        rv_face_detected.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun observeLiveData() {
        model.noResultContainerVisibility()
            .observe(viewLifecycleOwner, Observer { it?.let { setNoResultContainerVisibility(it) } })

        model.contentContainerVisibility()
            .observe(viewLifecycleOwner, Observer { it?.let { setContentContainerVisibility(it) } })

        model.getProgressState().observe(this, Observer { it?.let { setProgress(it) } })

        model.screenContent().observe(this, Observer { it?.let { setScreenContent(it) } })
    }

    private fun setScreenContent(content: List<IImageObj>) {
        (rv_face_detected.adapter as? IAdapter<List<IImageObj>>)?.setContent(content)
    }

    override fun setGui() {
        rv_face_detected.apply {
            layoutManager = GridLayoutManager(context.applicationContext, Constants.GRID_COLUMNS)
            adapter = FaceDetectedAdapter()
        }
    }

    override fun setListeners() {

    }
}