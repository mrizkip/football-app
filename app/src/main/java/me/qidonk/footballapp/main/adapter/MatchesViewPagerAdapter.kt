package me.qidonk.footballapp.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.notification_template_big_media.*

class MatchesViewPagerAdapter(fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()
    private val tabTitles = arrayOf("NEXT MATCH", "LAST MATCH")

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles.get(position)
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}