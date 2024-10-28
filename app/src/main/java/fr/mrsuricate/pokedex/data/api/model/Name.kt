package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.domain.model.Name
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    @SerializedName("name") val name: String = String(),
    @SerializedName("language") val language: NamedApiResource = NamedApiResource()
) : Parcelable {
    fun toDomain(): Name {
        return Name(
            language = this.language.name,
            name = this.name
        )
    }
}