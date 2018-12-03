package me.qidonk.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("strEvent")
    var match: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("intHomeScore")
    var scoreHome: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strHomeFormation")
    var formationHome: String? = null,

    @SerializedName("strHomeGoalDetails")
    var goalHomeDetails: String? = null,

    @SerializedName("intHomeShots")
    var shotsHome: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var goalKeeperHome: String? = null,

    @SerializedName("strHomeLineupDefense")
    var defenseHome: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var midfieldHome: String? = null,

    @SerializedName("strHomeLineupForward")
    var forwardHome: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var substitutesHome: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("intAwayScore")
    var scoreAway: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("strAwayFormation")
    var formationAway: String? = null,

    @SerializedName("strAwayGoalDetails")
    var goalAwayDetails: String? = null,

    @SerializedName("intAwayShots")
    var shotsAway: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var goalKeeperAway: String? = null,

    @SerializedName("strAwayLineupDefense")
    var defenseAway: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var midfieldAway: String? = null,

    @SerializedName("strAwayLineupForward")
    var forwardAway: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var substitutesAway: String? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null

) : Parcelable

