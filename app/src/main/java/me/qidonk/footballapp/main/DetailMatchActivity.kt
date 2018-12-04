package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.R.drawable.ic_add_to_favorites
import me.qidonk.footballapp.R.drawable.ic_added_to_favorites
import me.qidonk.footballapp.model.FavoriteMatch
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.DetailMatchPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.database
import me.qidonk.footballapp.view.DetailMatchView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.sql.SQLClientInfoException

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var mMatch: Match? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"

        val repository = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, repository, gson)

        val intent = intent
        val matchId = intent.getStringExtra("matchId")
        val homeTeamId = intent.getStringExtra("homeTeamId")
        val awayTeamId = intent.getStringExtra("awayTeamId")
        presenter.getDetailMatch(matchId)
        presenter.getHomeTeamLogo(homeTeamId)
        presenter.getAwayTeamLogo(awayTeamId)
    }

    override fun getMatchDetail(matches: List<Match>) {
        mMatch = matches[0]
        favoriteState()
        setFavorite()
        detailMatch_date.text = matches[0].matchDate
        detailMatch_time.text = matches[0].matchTime
        detailMatch_homeScore.text = matches[0].scoreHome
        detailMatch_awayScore.text = matches[0].scoreAway
        detailMatch_homeTeam.text = matches[0].homeTeam
        detailMatch_awayTeam.text = matches[0].awayTeam
        detailMatch_homeGoal.text = matches[0].goalHomeDetails
        detailMatch_awayGoal.text = matches[0].goalAwayDetails
        detailMatch_homeShots.text = matches[0].shotsHome
        detailMatch_awayShots.text = matches[0].shotsAway
        detailMatch_homeGoalkeeper.text = matches[0].goalKeeperHome
        detailMatch_awayGoalkeeper.text = matches[0].goalKeeperAway
        detailMatch_homeDefense.text = matches[0].defenseHome
        detailMatch_awayDefense.text = matches[0].defenseAway
        detailMatch_homeMidfield.text = matches[0].midfieldHome
        detailMatch_awayMidfield.text = matches[0].midfieldAway
        detailMatch_homeForward.text = matches[0].forwardHome
        detailMatch_awayForward.text = matches[0].forwardAway
    }

    override fun getHomeTeamLogo(teams: List<Team>) {
        Picasso.get().load(teams[0].teamLogo).into(detailMatch_homeLogo)
    }

    override fun getAwayTeamLogo(teams: List<Team>) {
        Picasso.get().load(teams[0].teamLogo).into(detailMatch_awayLogo)
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
                if (mMatch != null) {
                    if (isFavorite) removeFromFavorite() else addFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.MATCH_ID to mMatch?.matchId,
                    FavoriteMatch.DATE to mMatch?.matchDate,
                    FavoriteMatch.HOME_TEAM_ID to mMatch?.homeTeamId,
                    FavoriteMatch.AWAY_TEAM_ID to mMatch?.awayTeamId,
                    FavoriteMatch.HOME_TEAM to mMatch?.homeTeam,
                    FavoriteMatch.AWAY_TEAM to mMatch?.awayTeam,
                    FavoriteMatch.HOME_SCORE to mMatch?.scoreHome,
                    FavoriteMatch.AWAY_SCORE to mMatch?.scoreAway
                )
            }
            Snackbar.make(detailMatch_parent, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLClientInfoException) {
            Snackbar.make(detailMatch_parent, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})",
                    "id" to mMatch?.matchId.toString()
                )
            }
            Snackbar.make(detailMatch_parent, "Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLClientInfoException) {
            Snackbar.make(detailMatch_parent, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
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
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs(
                        "(MATCH_ID = {id})",
                        "id" to mMatch?.matchId.toString()
                    )
                val favorite = result.parseList(classParser<FavoriteMatch>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLClientInfoException) {
            Snackbar.make(detailMatch_parent, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }
}
