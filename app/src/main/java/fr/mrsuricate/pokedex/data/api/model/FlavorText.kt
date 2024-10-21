package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlavorText(
    @SerializedName("flavor_text") val flavorText: String? = null,
    @SerializedName("language") val language: NamedApiResource? = null,
    @SerializedName("version") val version: NamedApiResource? = null
) : Parcelable
