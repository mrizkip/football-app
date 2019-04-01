package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.qidonk.footballapp.datasource.api.TheSportDBApi
import me.qidonk.footballapp.model.Match
import me.qidonk.footballapp.model.Matches
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.TestContextProvider
import me.qidonk.footballapp.view.MatchView
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var apiResponse: Deferred<String>
    @Mock
    private lateinit var matchPresenter: MatchPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter(matchView, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = Matches(matches)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    Matches::class.java
                )
            ).thenReturn(response)

            matchPresenter.getLastMatch(leagueId)

            Mockito.verify(matchView).showLoading()
            Mockito.verify(matchView).showMatchList(matches)
            Mockito.verify(matchView).hideLoading()
        }
    }

    @Test
    fun getNextMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = Matches(matches)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    Matches::class.java
                )
            ).thenReturn(response)

            matchPresenter.getNextMatch(leagueId)

            Mockito.verify(matchView).showLoading()
            Mockito.verify(matchView).showMatchList(matches)
            Mockito.verify(matchView).hideLoading()
        }
    }
}