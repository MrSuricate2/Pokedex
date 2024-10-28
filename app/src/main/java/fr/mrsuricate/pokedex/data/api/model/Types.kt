package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.domain.model.Type
import kotlinx.parcelize.Parcelize

@Parcelize
data class Types(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = String(),
    @SerializedName("damage_relations") var damageRelations: TypeRelations? = null,
    @SerializedName("past_damage_relations") var pastDamageRelations: List<TypeRelationsPast?>? = null,
    @SerializedName("game_indices") var gameIndices: List<GenerationGameIndex?>? = null,
    @SerializedName("generation") var generation: NamedApiResource? = null,
    @SerializedName("move_damage_class") var moveDamageClass: NamedApiResource? = null,
    @SerializedName("names") var names: List<Name> = listOf(Name()),
    @SerializedName("pokemon") var pokemon: List<TypePokemon?>? = null,
    @SerializedName("moves") var moves: List<NamedApiResource?>? = null,
    @SerializedName("sprites") var sprites: PokemonSprites = PokemonSprites(),
) : Parcelable {
    fun toDomain(): Type {
        val listName: MutableList<fr.mrsuricate.pokedex.domain.model.Name> = mutableListOf()
        this.names.forEach { name ->
            listName.add(name.toDomain())
        }
        return Type(
            id = this.id,
            name = this.name,
            names = listName
        )
    }
}

