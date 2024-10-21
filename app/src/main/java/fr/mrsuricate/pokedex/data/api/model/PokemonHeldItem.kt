package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonHeldItem(
    @SerializedName("item") var item: NamedApiResource? = null,
    @SerializedName("version_details") var versionDetails: ArrayList<PokemonHeldItemVersion> = arrayListOf()
) : Parcelable