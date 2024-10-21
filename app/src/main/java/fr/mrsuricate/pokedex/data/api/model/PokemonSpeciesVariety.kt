package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesVariety(
    @SerializedName("is_default") val isDefault: Boolean? = null,
    @SerializedName("pokemon") val pokemon: NamedApiResource? = null
) : Parcelable
