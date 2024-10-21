package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crystal(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("back_shiny_transparent") var backShinyTransparent: String? = null,
    @SerializedName("back_transparent") var backTransparent: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("front_shiny_transparent") var frontShinyTransparent: String? = null,
    @SerializedName("front_transparent") var frontTransparent: String? = null
) : Parcelable