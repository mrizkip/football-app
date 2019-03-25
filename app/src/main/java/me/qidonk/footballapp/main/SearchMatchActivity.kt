package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
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

        searchMatch_recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MatchAdapter(this, matchList)
        searchMatch_recyclerView.adapter = adapter

        val repository = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, repository, gson)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // TODO: SearchView onCreate using SearchView from layout
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let { presenter.searchMatch(query) }
                return false
            }
        })

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                matchList.clear()
                adapter.notifyDataSetChanged()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
