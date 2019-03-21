package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Match

interface FavoriteMatchView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteMatch(matches: List<Match>)
}