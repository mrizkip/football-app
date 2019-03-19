package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Player

interface PlayerDetailView {
    fun showTeamDetail(data: Player)
}