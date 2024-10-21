package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerationI(
    @SerializedName("red-blue") var redBlue: RedBlue? = RedBlue(),
    @SerializedName("yellow") var yellow: Yellow? = Yellow()
) : Parcelable