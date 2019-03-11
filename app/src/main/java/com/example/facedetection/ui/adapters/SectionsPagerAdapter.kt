package com.example.facedetection.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.facedetection.ui.base.BaseFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list: List<BaseFragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size

    fun setContent(iList: List<BaseFragment>){
        list = iList
        notifyDataSetChanged()
    }
}