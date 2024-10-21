package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonMove(
    @SerializedName("move") var move: NamedApiResource? = null,
    @SerializedName("version_group_details") var versionGroupDetails: ArrayList<PokemonMoveVersion> = arrayListOf()
) : Parcelable