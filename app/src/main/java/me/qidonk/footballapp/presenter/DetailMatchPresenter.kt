package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.model.Teams
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.DetailMatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetailMatch(matchId: String?) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getDetailMatch(matchId)),
                Matches::class.java
            )

            uiThread {
                view.getMatchDetail(data.matches)
            }
        }
    }

    fun getHomeTeamLogo(teamId: String?) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                Teams::class.java
            )

            uiThread {
                view.getHomeTeamLogo(data.teams)
            }
        }
    }

    fun getAwayTeamLogo(teamId: String?) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                Teams::class.java
            )

            uiThread {
                view.getAwayTeamLogo(data.teams)
            }
        }
    }
}