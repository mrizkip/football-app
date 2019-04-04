package me.qidonk.footballapp.model

import com.google.gson.annotations.SerializedName

data class PlayerDetail(
        @SerializedName("players")
        var player: List<Player>
)