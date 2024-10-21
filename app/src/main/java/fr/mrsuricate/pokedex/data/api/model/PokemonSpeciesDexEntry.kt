package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesDexEntry(
    @SerializedName("entry_number") val entryNumber: Int? = null,
    @SerializedName("pokedex") val pokedex: NamedApiResource? = null
) : Parcelable
