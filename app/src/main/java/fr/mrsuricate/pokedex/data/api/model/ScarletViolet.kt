package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScarletViolet(
    @SerializedName("name_icon") val nameIcon: String? = null
) : Parcelable