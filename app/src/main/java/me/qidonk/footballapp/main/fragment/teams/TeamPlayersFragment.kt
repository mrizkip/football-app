package me.qidonk.footballapp.main.fragment.teams


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import kotlinx.android.synthetic.main.fragment_team_players.*
import kotlinx.android.synthetic.main.fragment_team_players.view.*

import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.PlayerDetailActivity
import me.qidonk.footballapp.main.adapter.PlayerAdapter
import me.qidonk.footballapp.model.Player
import me.qidonk.footballapp.presenter.TeamPlayerPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.TeamPlayerView

class TeamPlayersFragment : Fragment(), TeamPlayerView {

    private lateinit var presenter: TeamPlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private var players: MutableList<Player> = mutableListOf()

    companion object {
        fun newInstance(): TeamPlayersFragment {
            return TeamPlayersFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.teamPlayer_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PlayerAdapter(context, players) {
            val intent = Intent(context, PlayerDetailActivity::class.java)
                    .putExtra("playerId", it.playerId)
            startActivity(intent)
        }
        view.teamPlayer_recyclerView.adapter = adapter

        val repository = ApiRepository()
        val gson = Gson()
        presenter =  TeamPlayerPresenter(this, repository, gson)

        val teamId = arguments?.getString("teamId")
        presenter.getPlayerList(teamId)
    }

    override fun showTeamPlayers(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
