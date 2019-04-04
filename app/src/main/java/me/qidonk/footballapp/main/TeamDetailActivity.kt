package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.R.drawable.ic_add_to_favorites
import me.qidonk.footballapp.R.drawable.ic_added_to_favorites
import me.qidonk.footballapp.main.adapter.TeamDetailViewPagerAdapter
import me.qidonk.footballapp.main.fragment.teams.TeamDetailFragment
import me.qidonk.footballapp.main.fragment.teams.TeamPlayersFragment
import me.qidonk.footballapp.model.FavoriteTeam
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.TeamDetailPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.database
import me.qidonk.footballapp.view.TeamDetailView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.sql.SQLClientInfoException

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private var mTeam: Team? = null

    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val repository = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, repository, gson)

        val intent = intent
        val teamId = intent.getStringExtra("teamId")
        val bundle = Bundle()
        bundle.putString("teamId", teamId)

        presenter.getTeamDetail(teamId)

        setupViewPager(teamDetail_viewPager, bundle)
        teamDetail_tabLayout.setupWithViewPager(teamDetail_viewPager)
    }

    override fun showTeamDetail(data: Team) {
        mTeam = data
        favoriteState()
        setFavorite()
        teamDetail_teamName.text = mTeam?.teamName
        teamDetail_teamYear.text = mTeam?.teamBuildYear
        teamDetail_teamStadium.text = mTeam?.teamStadium
        Picasso.get().load(mTeam?.teamLogo).into(teamDetail_teamLogo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (mTeam != null) {
                    if (isFavorite) removeFromFavorite() else addFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewPager(viewPager: ViewPager?, bundle: Bundle) {
        val adapter = TeamDetailViewPagerAdapter(supportFragmentManager)
        val fragmentOverview = TeamDetailFragment.newInstance()
        val fragmentPlayers = TeamPlayersFragment.newInstance()

        fragmentOverview.arguments = bundle
        fragmentPlayers.arguments = bundle

        adapter.addFragment(fragmentOverview)
        adapter.addFragment(fragmentPlayers)

        viewPager?.adapter = adapter
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                        FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to mTeam?.teamId,
                        FavoriteTeam.TEAM_LOGO to mTeam?.teamLogo,
                        FavoriteTeam.TEAM_NAME to mTeam?.teamName,
                        FavoriteTeam.TEAM_BUILD_YEAR to mTeam?.teamBuildYear,
                        FavoriteTeam.TEAM_STADIUM to mTeam?.teamStadium,
                        FavoriteTeam.TEAM_DESCRIPTION to mTeam?.teamDescription
                )
            }
            Snackbar.make(teamDetail_content, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLClientInfoException) {
            Snackbar.make(teamDetail_content, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                        FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to mTeam?.teamId.toString()
                )
            }
            Snackbar.make(teamDetail_content, "Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLClientInfoException) {
            Snackbar.make(teamDetail_content, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        try {
            database.use {
                val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                        .whereArgs(
                                "(TEAM_ID = {id})",
                                "id" to mTeam?.teamId.toString()
                        )
                val favorite = result.parseList(classParser<FavoriteTeam>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLClientInfoException) {
            Snackbar.make(teamDetail_content, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }
}
