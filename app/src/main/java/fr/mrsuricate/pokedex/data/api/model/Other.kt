package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Other(
    @SerializedName("dream_world") var dreamWorld: DreamWorld = DreamWorld(),
    @SerializedName("home") var home: Home = Home(),
    @SerializedName("official-artwork") var officialArtwork: OfficialArtwork = OfficialArtwork(),
    @SerializedName("showdown") var showdown: Showdown = Showdown()
) : Parcelable