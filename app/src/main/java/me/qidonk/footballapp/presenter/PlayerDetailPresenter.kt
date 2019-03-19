package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.PlayerDetail
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.PlayerDetailView

class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContexProvider = CoroutineContexProvider()) {

    fun getPlayerDetail(playerId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                    PlayerDetail::class.java)

            view.showTeamDetail(data.player[0])
        }
    }
}