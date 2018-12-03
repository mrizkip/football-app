package me.qidonk.footballapp.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.MatchAdapter
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.presenter.MainPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.MainView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class LastMatchFragment : Fragment(), MainView {

    companion object {
        fun newInstance(): LastMatchFragment {
            val fragment = LastMatchFragment()
            return fragment
        }
    }

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String
    private lateinit var leagueId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.lastMatch_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MatchAdapter(context, matches, { match: Match -> showDetailMatch(match) })
        view.lastMatch_recyclerView.adapter = adapter

        val repository = ApiRepository()
        val gson = Gson()
        leagueName = "English Premiere League"
        leagueId = "4328"
        presenter = MainPresenter(this, repository, gson)
        presenter.getLastMatch(leagueId)

        lastMatch_swipeRefresh.onRefresh {
            matches.clear()
            presenter.getLastMatch(leagueId)
        }

    }

    private fun showDetailMatch(match: Match) {
        startActivity<DetailMatchActivity>("matchId" to match.matchId,
            "homeTeamId" to match.homeTeamId,
            "awayTeamId" to match.awayTeamId)
    }

    override fun showLoading() {
        lastMatch_progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        lastMatch_progressBar.visibility = View.GONE
    }

    override fun showMatchList(matches: List<Match>) {
        lastMatch_swipeRefresh.isRefreshing = false
        this.matches.clear()
        this.matches.addAll(matches)
        adapter.notifyDataSetChanged()
    }


}
