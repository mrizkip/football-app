package me.qidonk.footballapp.repository

import android.content.Context
import me.qidonk.footballapp.model.FavoriteMatch
import me.qidonk.footballapp.model.FavoriteTeam
import me.qidonk.footballapp.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class LocalRepository(private val context: Context) {

    fun getFavoriteMatch(): List<FavoriteMatch> {
        lateinit var matchList: List<FavoriteMatch>
        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteMatch>())
            matchList = favorites
        }
        return matchList
    }

    fun getFavoriteTeam(): List<FavoriteTeam> {
        lateinit var teamList: List<FavoriteTeam>
        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorites = result.parseList(classParser<FavoriteTeam>())
            teamList = favorites
        }
        return teamList
    }

}