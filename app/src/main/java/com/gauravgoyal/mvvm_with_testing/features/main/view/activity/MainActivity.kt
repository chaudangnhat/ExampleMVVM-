package com.gauravgoyal.mvvm_with_testing.features.main.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gauravgoyal.mvvm_with_testing.R
import com.gauravgoyal.mvvm_with_testing.features.main.view.adapter.ViewPagerAdapter

import com.gauravgoyal.mvvm_with_testing.lifecycle.SomeObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = this.javaClass.simpleName.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val adapter = ViewPagerAdapter(supportFragmentManager)
            view_pager.adapter = adapter
        }

        // for shake of
        lifecycle.addObserver(SomeObserver())
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }
}
