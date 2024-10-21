package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonTypePast(
    @SerializedName("generation") val generation: NamedApiResource? = null,
    @SerializedName("types") val types: List<PokemonType?>? = null
) : Parcelable