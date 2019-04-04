package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.model.Teams
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.TeamsView
import java.util.*

class TeamPresenter(
    private val teamView: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContexProvider = CoroutineContexProvider()
) {

    fun getTeamList(leagueId: String) {
        teamView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamsbyLeagueId(leagueId)).await(),
                Teams::class.java
            )
            teamView.hideLoading()
            teamView.showTeamList(data.teams)
        }
    }

    fun searchTeam(teamName: String) {
        teamView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.searchTeam(teamName)).await(),
                Teams::class.java
            )

            teamView.hideLoading()
            teamView.showTeamList(data.teams ?: Collections.emptyList())
        }
    }


}