package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContexProvider = CoroutineContexProvider()
) {

    fun getLastMatch(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLastMatch(leagueId)).await(),
                Matches::class.java
            )

            view.hideLoading()
            view.showMatchList(data.matches)
        }
    }

    fun getNextMatch(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(leagueId)).await(),
                Matches::class.java
            )

            view.hideLoading()
            view.showMatchList(data.matches)
        }
    }
}