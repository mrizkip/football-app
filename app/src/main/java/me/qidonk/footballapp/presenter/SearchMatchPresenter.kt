package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.MatchSearch
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.CoroutineContexProvider
import me.qidonk.footballapp.view.SearchMatchView
import java.util.*

class SearchMatchPresenter(
        private val view: SearchMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContexProvider = CoroutineContexProvider()
) {
    fun searchMatch(matchName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.searchMatch(matchName)).await(),
                    MatchSearch::class.java
            )

            view.hideLoading()
            view.showMatch(data.matches ?: Collections.emptyList())
        }
    }
}