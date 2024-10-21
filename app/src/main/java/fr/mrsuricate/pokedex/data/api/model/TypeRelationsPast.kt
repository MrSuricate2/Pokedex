package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeRelationsPast(
    @SerializedName("generation") val generation: NamedApiResource? = null,
    @SerializedName("damage_relations") val damageRelations: TypeRelations? = null
) : Parcelable