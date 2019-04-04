package me.qidonk.footballapp.main.fragment.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorites.*

import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.FavoriteViewPagerAdapter

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return FavoritesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager(favorites_viewPager)
        favorites_tabLayout.setupWithViewPager(favorites_viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = FavoriteViewPagerAdapter(childFragmentManager)
        val matchFragment = FavoriteMatchFragment.newInstance()
        val teamFragment = FavoriteTeamFragment.newInstance()

        adapter.addFragment(matchFragment)
        adapter.addFragment(teamFragment)

        viewPager?.adapter = adapter
    }
}
