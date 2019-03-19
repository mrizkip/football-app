package me.qidonk.footballapp.main.fragment.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.qidonk.footballapp.R

class FavoriteTeamFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteTeamFragment {
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }


}
