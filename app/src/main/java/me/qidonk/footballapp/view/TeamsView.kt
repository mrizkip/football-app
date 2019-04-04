package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Team

interface TeamsView {
    fun showTeamList(data: List<Team>)
    fun showLoading()
    fun hideLoading()
}