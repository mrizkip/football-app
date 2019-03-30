package me.qidonk.footballapp.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.model.Teams
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.utils.TestContextProvider
import me.qidonk.footballapp.view.TeamsView
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var teamPresenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        teamPresenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamListTest() {
        val teams: MutableList<Team> = mutableListOf()
        val response = Teams(teams)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    Teams::class.java
                )
            ).thenReturn(response)

            teamPresenter.getTeamList(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun searchTeamTest() {
    }
}