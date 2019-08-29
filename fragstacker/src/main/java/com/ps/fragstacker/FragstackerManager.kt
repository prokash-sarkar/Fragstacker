package com.ps.fragstacker

import androidx.fragment.app.Fragment
import java.util.*

/**
 * Created by Prokash Sarkar on 8/5/2019.
 * https://github.com/prokash-sarkar
 */

class FragstackerManager {

    private val mFragments = ArrayList<Fragment>()

    companion object {
        val instance = FragstackerManager()
    }

    fun addFragment(fragment: Fragment) {
        mFragments.add(fragment)
    }

    fun getFragment(position: Int): Fragment {
        return mFragments[position]
    }

    fun getAllFragments(): ArrayList<Fragment> {
        return mFragments
    }

    fun removeFragment(position: Int) {
        mFragments.removeAt(position)
    }

    fun removeFragment(fragment: Fragment) {
        mFragments.remove(fragment)
    }

    fun removeAllFragments() {
        mFragments.clear()
    }

}