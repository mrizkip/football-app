package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.model.Teams
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.DetailMatchView

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContexProvider = CoroutineContexProvider()
) {

    fun getDetailMatch(matchId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getDetailMatch(matchId)).await(),
                Matches::class.java
            )

            view.getMatchDetail(data.matches)
        }
    }

    fun getHomeTeamLogo(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                Teams::class.java
            )

            view.getHomeTeamLogo(data.teams)
        }
    }

    fun getAwayTeamLogo(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                Teams::class.java
            )

            view.getAwayTeamLogo(data.teams)
        }
    }
}