package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.model.Teams
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.repository.LocalRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.FavoriteTeamView

class FavoriteTeamPresenter(
        private val view: FavoriteTeamView,
        private val localRepository: LocalRepository,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContexProvider = CoroutineContexProvider()
) {

    fun getFavoriteTeam() {
        view.showLoading()

        val favList = localRepository.getFavoriteTeam()
        val teamList: MutableList<Team> = mutableListOf()
        for (fav in favList) {
            GlobalScope.launch(context.main) {
                val data = gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getTeamDetail(fav.teamId)).await(),
                        Teams::class.java
                )
                teamList.add(data.teams[0])
                view.hideLoading()
                view.showFavoriteTeam(teamList)
            }
        }

        if (favList.isEmpty()) {
            view.hideLoading()
            view.showFavoriteTeam(teamList)
        }
    }
}