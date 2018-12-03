package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matches: List<Match>)
}