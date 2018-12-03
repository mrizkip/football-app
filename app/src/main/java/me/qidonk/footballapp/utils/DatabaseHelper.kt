package me.qidonk.footballapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import me.qidonk.footballapp.model.FavoriteMatch
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteMatch.TABLE_FAVORITE_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatch.DATE to TEXT,
            FavoriteMatch.HOME_TEAM_ID to TEXT,
            FavoriteMatch.AWAY_TEAM_ID to TEXT,
            FavoriteMatch.HOME_TEAM to TEXT,
            FavoriteMatch.AWAY_TEAM to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

// Access property for context
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)