package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonCries(
    @SerializedName("latest") var latest: String? = null,
    @SerializedName("legacy") var legacy: String? = null
) : Parcelable