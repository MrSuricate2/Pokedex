package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FireredLeafgreen(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null
) : Parcelable