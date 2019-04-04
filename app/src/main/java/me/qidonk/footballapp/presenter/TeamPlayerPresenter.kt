package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Players
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.TeamPlayerView

class TeamPlayerPresenter(private val view: TeamPlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContexProvider = CoroutineContexProvider()) {
    fun getPlayerList(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamPlayers(teamId)).await(),
                    Players::class.java
            )

            view.showTeamPlayers(data.player)
        }
    }
}