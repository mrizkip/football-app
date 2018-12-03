package me.qidonk.footballapp.view

import me.qidonk.footballapp.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(matches: List<Match>)
}