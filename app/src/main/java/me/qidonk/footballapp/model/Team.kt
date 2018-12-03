package me.qidonk.footballapp.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeamBadge")
    var teamLogo: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("intFormedYear")
    var teamBuildYear: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
)