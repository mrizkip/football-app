package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Team

interface DetailMatchView {
    fun getMatchDetail(matches: List<Match>)
    fun getHomeTeamLogo(teams: List<Team>)
    fun getAwayTeamLogo(teams: List<Team>)
}