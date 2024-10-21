package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerationIII(
    @SerializedName("emerald") var emerald: Emerald? = Emerald(),
    @SerializedName("firered-leafgreen") var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
    @SerializedName("ruby-sapphire") var rubySapphire: RubySapphire? = RubySapphire()
) : Parcelable