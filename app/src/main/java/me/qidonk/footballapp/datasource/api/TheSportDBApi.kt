package me.qidonk.footballapp.datasource.api

import me.qidonk.footballapp.BuildConfig

object TheSportDBApi {
    fun getLastMatch(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + leagueId
    }

    fun getNextMatch(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + leagueId
    }

    fun getDetailMatch(matchId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + matchId
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }
}