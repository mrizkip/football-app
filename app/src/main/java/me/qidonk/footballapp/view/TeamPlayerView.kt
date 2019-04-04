package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Player

interface TeamPlayerView {
    fun showTeamPlayers(data: List<Player>)
}