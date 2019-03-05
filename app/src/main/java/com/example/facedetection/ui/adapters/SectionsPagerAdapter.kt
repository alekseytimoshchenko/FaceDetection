package com.example.facedetection.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list: List<Fragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = list[position]
    override fun getCount(): Int = list.size

    public fun setContent(iList: List<Fragment>){
        list = iList
        notifyDataSetChanged()
    }
}