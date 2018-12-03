package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.DetailMatchPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.DetailMatchView

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter

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
        val mMatch = matches[0]
        detailMatch_date.text = mMatch.matchDate
        detailMatch_homeScore.text = mMatch.scoreHome
        detailMatch_awayScore.text = mMatch.scoreAway
        detailMatch_homeTeam.text = mMatch.homeTeam
        detailMatch_awayTeam.text = mMatch.awayTeam
        detailMatch_homeGoal.text = mMatch.goalHomeDetails
        detailMatch_awayGoal.text = mMatch.goalAwayDetails
        detailMatch_homeShots.text = mMatch.shotsHome
        detailMatch_awayShots.text = mMatch.shotsAway
        detailMatch_homeGoalkeeper.text = mMatch.goalKeeperHome
        detailMatch_awayGoalkeeper.text = mMatch.goalKeeperAway
        detailMatch_homeDefense.text = mMatch.defenseHome
        detailMatch_awayDefense.text = mMatch.defenseAway
        detailMatch_homeMidfield.text = mMatch.midfieldHome
        detailMatch_awayMidfield.text = mMatch.midfieldAway
        detailMatch_homeForward.text = mMatch.forwardHome
        detailMatch_awayForward.text = mMatch.forwardAway
    }

    override fun getHomeTeamLogo(teams: List<Team>) {
        Picasso.get().load(teams[0].teamLogo).into(detailMatch_homeLogo)
    }

    override fun getAwayTeamLogo(teams: List<Team>) {
        Picasso.get().load(teams[0].teamLogo).into(detailMatch_awayLogo)
    }
}
