package com.ps.fragstacker.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ps.fragstacker.FragmentTransactionOptions
import com.ps.fragstacker.FragstackerController
import com.ps.fragstacker.FragstakerListener

class MainActivity : AppCompatActivity(), FragstakerListener {

    private val TAG = "MainActivity"

    private lateinit var fragstackerController: FragstackerController
    private lateinit var fragmentTransactionOptions: FragmentTransactionOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragstackerController = FragstackerController(
            supportFragmentManager,
            R.id.content_main,
            this
        )

        fragmentTransactionOptions = FragmentTransactionOptions.Builder()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .build()

        fragstackerController.addFragment(
            FragmentA.newInstance(""),
            fragmentTransactionOptions, false
        )
    }

    override fun onFragmentAdded(tag: String?) {
        Log.i(TAG, "onFragmentAdded() $tag")
    }

    override fun onFragmentRemoved(tag: String?) {
        Log.i(TAG, "onFragmentRemoved() $tag")
    }

    override fun onReachedLastFragment(tag: String?) {
        Log.i(TAG, "onReachedLastFragment() $tag")

        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.exit_title))
        builder.setMessage(getString(R.string.exit_desc))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { dialog, id ->
                finish()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun onBackPressed() {
        // Removes the current fragment and loads the immediate previous one from stack
        fragstackerController.gotoPreviousFragment(fragmentTransactionOptions)
    }

}
