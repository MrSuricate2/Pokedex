package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.domain.model.Language
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    @SerializedName("id") var id: Int = 5,
    @SerializedName("name") var name: String = "fr",
    @SerializedName("official") var official: Boolean? = null,
    @SerializedName("iso639") var iso639: String = "fr",
    @SerializedName("iso3166") var iso3166: String = "fr",
    @SerializedName("names") var names: List<Name> = listOf()
) : Parcelable {
    fun toDomain(): Language {
        return Language(
            language = this.name,
            names = this.names.map { it.toDomain() }
        )
    }
}