package me.qidonk.footballapp.main.fragment.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.SearchMatchActivity
import me.qidonk.footballapp.main.adapter.MatchAdapter
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.presenter.MatchPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.MatchView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class NextMatchFragment : Fragment(), MatchView {

    companion object {
        fun newInstance(): Fragment {
            val fragment = NextMatchFragment()
            return fragment
        }
    }

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String
    private lateinit var leagueId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.nextMatch_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MatchAdapter(context, matches)
        view.nextMatch_recyclerView.adapter = adapter

        val repository = ApiRepository()
        val gson = Gson()
        leagueName = "English Premier League"
        leagueId = "4328"
        presenter = MatchPresenter(this, repository, gson)
        presenter.getNextMatch(leagueId)

        val spinnerItems = resources.getStringArray(R.array.league_array)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        nextMatch_spinner.adapter = spinnerAdapter
        nextMatch_spinner.setSelection(0)

        nextMatch_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = nextMatch_spinner.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> {
                        leagueId = "4328"
                        presenter.getNextMatch(leagueId)
                    }
                    "German Bundesliga" -> {
                        leagueId = "4331"
                        presenter.getNextMatch(leagueId)
                    }
                    "Italian Serie A" -> {
                        leagueId = "4332"
                        presenter.getNextMatch(leagueId)
                    }
                    "French Ligue 1" -> {
                        leagueId = "4334"
                        presenter.getNextMatch(leagueId)

                    }
                    "Spanish La Liga" -> {
                        leagueId = "4335"
                        presenter.getNextMatch(leagueId)

                    }
                    "Netherlands Eredivisie" -> {
                        leagueId = "4336"
                        presenter.getNextMatch(leagueId)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        nextMatch_swipeRefresh.onRefresh {
            matches.clear()
            presenter.getNextMatch(leagueId)
        }
    }

    override fun showLoading() {
        nextMatch_progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        nextMatch_progressBar?.visibility = View.GONE
    }

    override fun showMatchList(matches: List<Match>) {
        nextMatch_swipeRefresh?.isRefreshing = false
        this.matches.clear()
        this.matches.addAll(matches)
        adapter.notifyDataSetChanged()
    }
}
