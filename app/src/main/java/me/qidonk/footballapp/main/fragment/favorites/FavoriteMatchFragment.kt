package me.qidonk.footballapp.main.fragment.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.MatchAdapter
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.presenter.FavoriteMatchPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.repository.LocalRepository
import me.qidonk.footballapp.view.FavoriteMatchView
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private var favoritesMatch: MutableList<Match> = mutableListOf()
    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var adapter: MatchAdapter

    companion object {
        fun newInstance(): FavoriteMatchFragment {
            return FavoriteMatchFragment()
        }
    }

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
        adapter = MatchAdapter(context, favoritesMatch)
        view.favoritematch_recyclerView.adapter = adapter

        val localRepository = LocalRepository(context!!)
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = FavoriteMatchPresenter(this, localRepository, apiRepository, gson)
        presenter.getFavoriteMatch()

        view.favoriteMatch_swipeRefresh.onRefresh {
            favoritesMatch.clear()
            presenter.getFavoriteMatch()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMatch()
    }

    override fun showLoading() {
        favoriteMatch_progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        favoriteMatch_progressBar?.visibility = View.GONE
    }

    override fun showFavoriteMatch(matches: List<Match>) {
        favoriteMatch_swipeRefresh?.isRefreshing = false
        favoritesMatch.clear()
        favoritesMatch.addAll(matches)
        adapter.notifyDataSetChanged()
    }

}
