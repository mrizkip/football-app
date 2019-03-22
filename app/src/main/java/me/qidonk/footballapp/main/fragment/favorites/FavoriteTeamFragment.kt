package me.qidonk.footballapp.main.fragment.favorites


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.TeamAdapter
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.FavoriteTeamPresenter
import me.qidonk.footballapp.view.FavoriteTeamView

import kotlinx.android.synthetic.main.fragment_favorite_team.*
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*
import me.qidonk.footballapp.main.TeamDetailActivity
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.repository.LocalRepository
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    private var favoriteTeam: MutableList<Team> = mutableListOf()
    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var adapter: TeamAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.favoriteTeam_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TeamAdapter(context, favoriteTeam) {
            val intent = Intent(context, TeamDetailActivity::class.java)
                .putExtra("teamId", it.teamId)
            startActivity(intent)
        }
        view.favoriteTeam_recyclerView.adapter = adapter

        val localRepository = LocalRepository(context!!)
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = FavoriteTeamPresenter(this, localRepository, apiRepository, gson)
        presenter.getFavoriteTeam()

        view.favoriteTeam_swipeRefresh.onRefresh {
            favoriteTeam.clear()
            presenter.getFavoriteTeam()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTeam()
    }

    override fun showLoading() {
        favoriteTeam_progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        favoriteTeam_progressBar?.visibility = View.GONE
    }

    override fun showFavoriteTeam(teams: List<Team>) {
        favoriteTeam_swipeRefresh?.isRefreshing = false
        favoriteTeam.clear()
        favoriteTeam.addAll(teams)
        adapter.notifyDataSetChanged()
    }
}
