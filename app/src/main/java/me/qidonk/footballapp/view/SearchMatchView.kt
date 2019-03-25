package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(matches: List<Match>)
}