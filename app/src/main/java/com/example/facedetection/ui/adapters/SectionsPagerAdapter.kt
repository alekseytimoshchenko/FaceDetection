package com.example.facedetection.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.facedetection.ui.base.IBaseFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list: List<IBaseFragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = list[position] as Fragment

    override fun getCount(): Int = list.size

    fun setContent(iList: List<IBaseFragment>){
        list = iList
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? = list[position].getTitle()
}