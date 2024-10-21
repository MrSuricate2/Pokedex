package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PastAbilitiesItem(
    @SerializedName("abilities") val abilities: List<PokemonAbility?>? = null,
    @SerializedName("generation") val generation: NamedApiResource? = null
) : Parcelable