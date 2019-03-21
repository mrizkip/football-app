package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.repository.LocalRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.FavoriteMatchView

class FavoriteMatchPresenter(
        private val view: FavoriteMatchView,
        private val localRepository: LocalRepository,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContexProvider = CoroutineContexProvider()
) {

    fun getFavoriteMatch() {
        view.showLoading()

        val favList = localRepository.getFavoriteMatch()
        val matchList: MutableList<Match> = mutableListOf()
        for (fav in favList) {
            GlobalScope.launch(context.main) {
                val data = gson.fromJson(
                        apiRepository
                                .doRequest(TheSportDBApi.getDetailMatch(fav.matchId)).await(),
                        Matches::class.java
                )
                matchList.add(data.matches[0])
                view.hideLoading()
                view.showFavoriteMatch(matchList)
            }
        }

        if (favList.isEmpty()) {
            view.hideLoading()
            view.showFavoriteMatch(matchList)
        }
    }
}