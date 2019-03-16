package com.example.facedetection.utils.diff

import android.support.v7.util.DiffUtil

class MyDiff<TYPE : IDiff<TYPE>>(
    private val oldList: List<TYPE>,
    private val newList: List<TYPE>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        val old = oldList[oldPos]
        val new = newList[newPos]

        return old.areItemsTheSame(new)
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val old = oldList[oldPos]
        val new = newList[newPos]

        return old.areContentsTheSame(new)
    }
}