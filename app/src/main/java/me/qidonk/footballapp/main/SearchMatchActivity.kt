package me.qidonk.footballapp.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.adapter.MatchAdapter
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.presenter.SearchMatchPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.SearchMatchView

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private var matchList: MutableList<Match> = mutableListOf()
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        setupSearchView()

        searchMatch_recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MatchAdapter(this, matchList) {
            val intent = Intent(this, DetailMatchActivity::class.java)
                .putExtra("matchId", it.matchId)
                .putExtra("homeTeamId", it.homeTeamId)
                .putExtra("awayTeamId", it.awayTeamId)
            startActivity(intent)
        }
        searchMatch_recyclerView.adapter = adapter

        val repository = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, repository, gson)
    }

    private fun setupSearchView() {
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchMatch_searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        searchMatch_searchView.isIconified = false

        val edtSearch: EditText = searchMatch_searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
        edtSearch.setHintTextColor(Color.LTGRAY)
        edtSearch.setTextColor(Color.WHITE)

        searchMatch_searchView.queryHint = "Search Matches"

        searchMatch_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.equals("")) {
                    matchList.clear()
                    adapter.notifyDataSetChanged()
                } else {
                    query?.let { presenter.searchMatch(query) }
                }
                return false
            }
        })

        searchMatch_searchView.setOnCloseListener {
            matchList.clear()
            adapter.notifyDataSetChanged()
            true
        }
    }

    override fun showLoading() {
        searchMatch_progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        searchMatch_progressBar.visibility = View.GONE
    }

    override fun showMatch(matches: List<Match>) {
        matchList.clear()
        matchList.addAll(matches)
        adapter.notifyDataSetChanged()
    }
}
