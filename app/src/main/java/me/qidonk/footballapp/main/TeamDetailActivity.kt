package me.qidonk.footballapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import me.qidonk.footballapp.R
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.TeamPresenter

class TeamDetailActivity : AppCompatActivity() {

    private var team: Team? = null
    private lateinit var presemter: TeamPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
    }
}
