package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    @SerializedName("name") val name: String = String(),
    @SerializedName("language") val language: NamedApiResource = NamedApiResource()
) : Parcelable