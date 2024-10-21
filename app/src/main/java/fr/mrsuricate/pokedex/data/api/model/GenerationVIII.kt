package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerationVIII(
    @SerializedName("icons") var icons: Icons? = Icons()
) : Parcelable