package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenerationIV(
    @SerializedName("diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerializedName("heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerializedName("platinum") var platinum: Platinum? = Platinum()
) : Parcelable