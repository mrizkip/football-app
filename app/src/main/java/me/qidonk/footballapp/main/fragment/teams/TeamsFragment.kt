package me.qidonk.footballapp.main.fragment.teams


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.fragment_teams.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.TeamDetailActivity
import me.qidonk.footballapp.main.adapter.TeamAdapter
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.TeamPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.TeamsView
import org.jetbrains.anko.support.v4.onRefresh

class TeamsFragment : Fragment(), TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String
    private lateinit var leagueId: String

    companion object {
        fun newInstance(): Fragment {
            return TeamsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        view.teams_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TeamAdapter(context, teams) {
            val intent = Intent(context, TeamDetailActivity::class.java)
                    .putExtra("teamId", it.teamId)
            startActivity(intent)
        }
        view.teams_recyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        leagueName = "English Premier League"
        leagueId = "4328"
        presenter = TeamPresenter(this, request, gson)
        presenter.getTeamList(leagueId)

        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        teams_spinner.adapter = spinnerAdapter

        teams_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(paremt: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = teams_spinner.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> {
                        leagueId = "4328"
                        presenter.getTeamList(leagueId)
                    }
                    "German Bundesliga" -> {
                        leagueId = "4331"
                        presenter.getTeamList(leagueId)
                    }
                    "Italian Serie A" -> {
                        leagueId = "4332"
                        presenter.getTeamList(leagueId)
                    }
                    "French Ligue 1" -> {
                        leagueId = "4334"
                        presenter.getTeamList(leagueId)

                    }
                    "Spanish La Liga" -> {
                        leagueId = "4335"
                        presenter.getTeamList(leagueId)

                    }
                    "Netherlands Eredivisie" -> {
                        leagueId = "4336"
                        presenter.getTeamList(leagueId)
                    }
                }
            }

            override fun onNothingSelected(paremt: AdapterView<*>?) {}
        }

        teams_swipeRefresh.onRefresh {
            teams.clear()
            presenter.getTeamList(leagueId)
        }
    }

    override fun showLoading() {
        teams_progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        teams_progressBar?.visibility = View.GONE
    }

    override fun showTeamList(data: List<Team>) {
        teams_swipeRefresh?.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.menu_search)

        val searchManager: SearchManager =  activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView?.queryHint = "Search teams"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.equals("", true)) {
                    presenter.getTeamList("4328")
                } else {
                    query?.let { presenter.searchTeam(it) }
                }
                return false
            }
        })

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                presenter.getTeamList("4328")
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}
