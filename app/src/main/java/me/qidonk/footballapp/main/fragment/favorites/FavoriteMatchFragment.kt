package me.qidonk.footballapp.main.fragment.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*

import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.FavoriteMatchAdapter
import me.qidonk.footballapp.model.FavoriteMatch
import me.qidonk.footballapp.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteMatchFragment {
            return FavoriteMatchFragment()
        }
    }

    private var favoritesMatch: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.favoritematch_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteMatchAdapter(context, favoritesMatch)
        view.favoritematch_recyclerView.adapter = adapter

        showFavorites()

        favoriteMatch_swipeRefresh.onRefresh {
            showFavorites()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorites()
    }

    private fun showFavorites() {
        favoriteMatch_progressBar.visibility = View.VISIBLE
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteMatch>())
            favoriteMatch_swipeRefresh.isRefreshing = false
            favoritesMatch.clear()
            favoritesMatch.addAll(favorites)
            adapter.notifyDataSetChanged()
            favoriteMatch_progressBar.visibility = View.GONE
        }
    }
}
