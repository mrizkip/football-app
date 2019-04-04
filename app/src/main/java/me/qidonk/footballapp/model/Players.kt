package me.qidonk.footballapp.model

import com.google.gson.annotations.SerializedName

data class Players(
        @SerializedName("player")
        var player: List<Player>
)