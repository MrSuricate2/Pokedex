package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.domain.model.Stats
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonStat(
    @SerializedName("base_stat") var baseStat: Int = 0,
    @SerializedName("effort") var effort: Int = 0,
    @SerializedName("stat") var stat: NamedApiResource = NamedApiResource()
) : Parcelable {
    fun toDomain(): Stats {
        return Stats(
            name = this.stat.name,
            baseStat = this.baseStat
        )
    }
}