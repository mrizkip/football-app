package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.MatchesViewPagerAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager(main_viewPager)
        main_tabLayout.setupWithViewPager(main_viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MatchesViewPagerAdapter(supportFragmentManager)
        val lastMatchFragment = LastMatchFragment.newInstance()
        val nextMatchFragment = NextMatchFragment.newInstance()

        adapter.addFragment(lastMatchFragment)
        adapter.addFragment(nextMatchFragment)

        viewPager.adapter = adapter
    }
}
