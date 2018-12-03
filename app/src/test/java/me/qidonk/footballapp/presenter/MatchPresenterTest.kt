package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.MatchView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var matchView: MatchView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var matchPresenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter(matchView, apiRepository, gson)
    }

    @Test
    fun getLastMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = Matches(matches)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getLastMatch(leagueId)),
            Matches::class.java)).thenReturn(response)

        matchPresenter.getLastMatch(leagueId)

        verify(matchView).showLoading()
        verify(matchView).showMatchList(matches)
        verify(matchView).hideLoading()
    }

    @Test
    fun getNextMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = Matches(matches)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository
            .doRequest(TheSportDBApi.getNextMatch(leagueId)),
            Matches::class.java)).thenReturn(response)

        matchPresenter.getNextMatch(leagueId)
        Thread.sleep(100) //be sure that the async part is executed

        verify(matchView).showLoading()
        verify(matchView).showMatchList(matches)
        verify(matchView).hideLoading()
    }
}