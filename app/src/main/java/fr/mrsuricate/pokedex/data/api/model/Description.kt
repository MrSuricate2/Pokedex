package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Description(
    @SerializedName("description") val description: String? = null,
    @SerializedName("language") val language: NamedApiResource? = null
) : Parcelable
