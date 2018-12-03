package me.qidonk.footballapp.model

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("events")
    val matches: List<Match>
)