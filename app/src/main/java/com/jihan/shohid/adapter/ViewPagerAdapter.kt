package com.jihan.shohid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(manager:FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(manager,lifecycle) {

    private val arrayList = ArrayList<Fragment>()

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return arrayList[position]
    }



    fun addFragment(fragment: Fragment){
        arrayList.add(fragment)
    }

}