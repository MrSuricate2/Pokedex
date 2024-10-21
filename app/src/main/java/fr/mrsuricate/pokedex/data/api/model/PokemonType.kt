package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonType(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: NamedApiResource = NamedApiResource()
) : Parcelable