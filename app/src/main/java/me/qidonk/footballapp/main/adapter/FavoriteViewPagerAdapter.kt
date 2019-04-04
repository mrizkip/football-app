package me.qidonk.footballapp.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FavoriteViewPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {
    private val fragments = ArrayList<Fragment>()

    private val tabTitles = arrayOf("MATCHES", "TEAMS")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}