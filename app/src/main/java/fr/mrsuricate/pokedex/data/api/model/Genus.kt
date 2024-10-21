package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genus(
    @SerializedName("genus") val genus: String? = null,
    @SerializedName("language") val language: NamedApiResource? = null
) : Parcelable
