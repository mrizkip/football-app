package me.qidonk.footballapp.model

import com.google.gson.annotations.SerializedName

data class MatchSearch(
        @SerializedName("event")
        val matches: List<Match>
)