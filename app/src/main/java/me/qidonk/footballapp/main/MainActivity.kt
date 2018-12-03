package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.qidonk.footballapp.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_prevMatch -> loadFragment(LastMatchFragment.newInstance(), savedInstanceState)
                R.id.menu_nextMatch -> loadFragment(NextMatchFragment.newInstance(), savedInstanceState)
                R.id.menu_favorites -> loadFragment(FavoriteFragment.newInstance(), savedInstanceState)
            }
            true
        }
        main_bottomNavigation.selectedItemId = R.id.menu_prevMatch
    }

    private fun loadFragment(fragment: Fragment, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frameLayout, fragment)
                .commit()
        }
    }

}
