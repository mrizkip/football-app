package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Team

interface TeamDetailView {
    fun showTeamDetail(data: Team)
}