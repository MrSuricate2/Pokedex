package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonAbility(
    @SerializedName("is_hidden") val isHidden: Boolean? = null,
    @SerializedName("slot") val slot: Int? = null,
    @SerializedName("ability") val ability: NamedApiResource? = null,
) : Parcelable