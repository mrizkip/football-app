package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLastMatch(leagueId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLastMatch(leagueId)),
                Matches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }

    fun getNextMatch(leagueId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(leagueId)),
                Matches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }
}