package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSprites(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_female") var backFemale: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
    @SerializedName("other") var other: Other = Other(),
    @SerializedName("versions") var versions: Versions? = Versions()

) : Parcelable