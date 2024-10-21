package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypePokemon(
    @SerializedName("pokemon") val pokemon: NamedApiResource? = null,
    @SerializedName("slot") val slot: Int? = null
) : Parcelable