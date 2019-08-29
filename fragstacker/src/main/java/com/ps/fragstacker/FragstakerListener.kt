package com.ps.fragstacker

/**
 * @Author: Prokash Sarkar
 * @Date: 8/5/19
 */

interface FragstakerListener {

    fun onFragmentAdded(tag: String?)

    fun onFragmentRemoved(tag: String?)

    fun onReachedLastFragment(tag: String?)

}