package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.fragment.favorites.FavoritesFragment
import me.qidonk.footballapp.main.fragment.matches.MatchesFragment
import me.qidonk.footballapp.main.fragment.teams.TeamsFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_matches -> loadFragment(MatchesFragment.newInstance(), savedInstanceState)
                R.id.menu_teams -> loadFragment(TeamsFragment.newInstance(), savedInstanceState)
                R.id.menu_favorites -> loadFragment(FavoritesFragment.newInstance(), savedInstanceState)
            }
            true
        }
        main_bottomNavigation.selectedItemId = R.id.menu_matches
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
