package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VersionGameIndex(
    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: NamedApiResource? = null
) : Parcelable