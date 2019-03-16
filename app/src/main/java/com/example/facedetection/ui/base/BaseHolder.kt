package com.example.facedetection.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseHolder<TYPE>(view: View) : IBaseHolder<TYPE>, RecyclerView.ViewHolder(view)