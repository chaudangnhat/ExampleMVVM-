package com.gauravgoyal.mvvm_with_testing.features.main.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gauravgoyal.mvvm_with_testing.features.news.view.fragment.ArticleListFragment
import com.gauravgoyal.mvvm_with_testing.features.userinfo.view.fragment.UserInfoFragment

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override
    fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ArticleListFragment()
            1 -> fragment = UserInfoFragment()
        }

        return fragment!!
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }

}