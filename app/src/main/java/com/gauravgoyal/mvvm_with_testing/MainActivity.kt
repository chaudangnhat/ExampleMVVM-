package com.gauravgoyal.mvvm_with_testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.gauravgoyal.mvvm_with_testing.lifecycle.SomeObserver
import com.gauravgoyal.mvvm_with_testing.news.view.fragment.ArticleListFragment

class MainActivity : AppCompatActivity() {

    final val TAG = this.javaClass.simpleName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ArticleListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ArticleListFragment.TAG).commit()
        }

        // for shake of
        lifecycle.addObserver(SomeObserver())
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }
}
