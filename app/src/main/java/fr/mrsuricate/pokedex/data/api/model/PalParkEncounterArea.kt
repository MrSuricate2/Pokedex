package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PalParkEncounterArea(
    @SerializedName("base_score") val baseScore: Int? = null,
    @SerializedName("rate") val rate: Int? = null,
    @SerializedName("area") val area: NamedApiResource? = null,
) : Parcelable
