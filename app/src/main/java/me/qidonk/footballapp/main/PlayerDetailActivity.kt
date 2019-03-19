package me.qidonk.footballapp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.model.Player
import me.qidonk.footballapp.presenter.PlayerDetailPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.PlayerDetailView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    private val log = AnkoLogger(this::class.java)

    private lateinit var presenter: PlayerDetailPresenter
    private var mPlayer: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0f

        val repository = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, repository, gson)

        val intent = intent
        val playerId = intent.getStringExtra("playerId")
        log.info { "Player Id: $playerId" }

        presenter.getPlayerDetail(playerId)
    }

    override fun showTeamDetail(data: Player) {
        mPlayer = data
        Picasso.get().load(mPlayer?.image).into(playerDetail_playerImage)
        playerDetail_playerName.text = mPlayer?.name
        playerDetail_playerPosition.text = mPlayer?.position
        playerDetail_tvHeight.text = mPlayer?.playerHeight
        playerDetail_tvtWeight.text = mPlayer?.playerWeight
        playerDetail_description.text = mPlayer?.playerDesc
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
