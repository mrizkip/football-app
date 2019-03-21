package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Team

interface FavoriteTeamView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteTeam(teams: List<Team>)
}