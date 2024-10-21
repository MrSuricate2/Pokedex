package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfficialArtwork(
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null
) : Parcelable