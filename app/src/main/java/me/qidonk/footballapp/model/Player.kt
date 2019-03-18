package me.qidonk.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("idPlayer")
        var playerId: String?,

        @SerializedName("strCutout")
        var image: String?,

        @SerializedName("strPlayer")
        var name: String?,

        @SerializedName("strPosition")
        var position: String?,

        @SerializedName("strFanart1")
        var fanart: String?,

        @SerializedName("strDescriptionEN")
        var playerDesc: String?,

        @SerializedName("strHeight")
        var playerHeight: String?,

        @SerializedName("strWeight")
        var playerWeight: String?
) : Parcelable