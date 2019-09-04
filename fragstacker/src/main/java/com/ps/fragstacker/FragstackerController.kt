package com.ps.fragstacker

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by Prokash Sarkar on 8/5/2019.
 * https://github.com/prokash-sarkar
 *
 *<p> A small utility class to manage Android fragments </p>
 *
 */

class FragstackerController(
    private val supportFragmentManager: FragmentManager, @IdRes private val containerId: Int,
    private val fragstakerListener: FragstakerListener
) {

    private val mFragstackerManager: FragstackerManager = FragstackerManager.instance

    /**
     * Adds a fragment to the stack and shows it in the container
     *
     * @param fragment requested Fragment
     * @param fragmentTransactionOptions custom transaction animation
     * @param allowDuplicates check if you want to allow adding the top fragment twice
     */
    fun addFragment(
        fragment: Fragment,
        fragmentTransactionOptions: FragmentTransactionOptions,
        allowDuplicates: Boolean
    ) {

        val tag = fragment::class.java.simpleName

        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(tag)

        // check if the top fragment and the requested fragment is same
        // we don't want to add the same fragment twice
        val topFragment = supportFragmentManager.findFragmentById(containerId)
        if (allowDuplicates || topFragment == null || topFragment.javaClass != fragment.javaClass) {

            fragstakerListener.onFragmentAdded(tag)

            transaction.add(containerId, fragment, tag)
            transaction.commit()

            mFragstackerManager.addFragment(fragment)

            showFragment(fragment, fragmentTransactionOptions)
        }
    }

    /**
     * Display the requested fragment without adding it in the fragment stack
     *
     * @param fragment requested Fragment
     * @param fragmentTransactionOptions custom transaction animation
     */
    private fun showFragment(
        fragment: Fragment,
        fragmentTransactionOptions: FragmentTransactionOptions
    ) {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(
            fragmentTransactionOptions.enter,
            fragmentTransactionOptions.exit,
            fragmentTransactionOptions.popEnter,
            fragmentTransactionOptions.popExit
        )

        transaction.show(fragment)
        transaction.commit()

        // hide all inactive fragments
        for (f in mFragstackerManager.getAllFragments()) {
            if (f.tag != null) {
                if (f.tag != fragment.tag) {
                    val t = supportFragmentManager.beginTransaction()
                    t.hide(f)
                    t.commit()
                }
            }
        }
    }

    /**
     * Removes current fragment and loads the immediate previous fragment from stack
     *
     * @param fragmentTransactionOptions custom transaction animation
     */
    fun gotoPreviousFragment(fragmentTransactionOptions: FragmentTransactionOptions) {
        val fragments = ArrayList(mFragstackerManager.getAllFragments())

        // there's should be more than 1 fragment
        if (fragments.size > 1) {
            val transaction = supportFragmentManager.beginTransaction()
            fragstakerListener.onFragmentRemoved(fragments[fragments.size - 1].tag)

            // remove the fragment from FragmentTransaction
            transaction.remove(fragments[fragments.size - 1])
            transaction.commit()

            // also remove the fragment from FragstackerManager
            mFragstackerManager.removeFragment(fragments.size - 1)

            // show the fragment before the removed one
            showFragment(fragments[fragments.size - 2], fragmentTransactionOptions)
        } else {
            if (fragments.size == 1) {
                fragstakerListener.onReachedLastFragment(fragments[0].tag)
            } else {
                fragstakerListener.onReachedLastFragment("")
            }
        }
    }

    /**
     * Perform a forceful removal of all fragments from the FragstackerManager
     * As well as from the FragmentTransaction
     */
    fun clearFragmentStack() {
        val transaction = supportFragmentManager.beginTransaction()

        for (f in ArrayList(mFragstackerManager.getAllFragments())) {
            if (f.tag != null) {
                fragstakerListener.onFragmentRemoved(f.tag)

                // remove the fragment from FragmentTransaction
                transaction.remove(f)

                // also remove the fragment from FragstackerManager
                mFragstackerManager.removeFragment(f)
            }
        }

        transaction.commit()
    }

}