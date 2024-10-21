package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerationGameIndex(
    @SerializedName("generation") val generation: NamedApiResource? = null,
    @SerializedName("game_index") val gameIndex: Int? = null
) : Parcelable