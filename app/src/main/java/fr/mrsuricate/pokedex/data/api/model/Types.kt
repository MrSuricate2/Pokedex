package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.ui.theme.bug
import fr.mrsuricate.pokedex.ui.theme.colorPrimary
import fr.mrsuricate.pokedex.ui.theme.dark
import fr.mrsuricate.pokedex.ui.theme.dragon
import fr.mrsuricate.pokedex.ui.theme.electric
import fr.mrsuricate.pokedex.ui.theme.fairy
import fr.mrsuricate.pokedex.ui.theme.fighting
import fr.mrsuricate.pokedex.ui.theme.fire
import fr.mrsuricate.pokedex.ui.theme.flying
import fr.mrsuricate.pokedex.ui.theme.ghost
import fr.mrsuricate.pokedex.ui.theme.grass
import fr.mrsuricate.pokedex.ui.theme.gray_21
import fr.mrsuricate.pokedex.ui.theme.ground
import fr.mrsuricate.pokedex.ui.theme.ice
import fr.mrsuricate.pokedex.ui.theme.poison
import fr.mrsuricate.pokedex.ui.theme.psychic
import fr.mrsuricate.pokedex.ui.theme.rock
import fr.mrsuricate.pokedex.ui.theme.steel
import fr.mrsuricate.pokedex.ui.theme.water
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
    @SerializedName("names") var names: List<Name?> = listOf(Name()),
    @SerializedName("pokemon") var pokemon: List<TypePokemon?>? = null,
    @SerializedName("moves") var moves: List<NamedApiResource?>? = null,
    @SerializedName("sprites") var sprites: PokemonSprites = PokemonSprites(),
) : Parcelable {
    fun getColor(): Color {
        val color: Color
        when (id) {
            1 -> color = gray_21
            2 -> color = fighting
            3 -> color = flying
            4 -> color = poison
            5 -> color = ground
            6 -> color = rock
            7 -> color = bug
            8 -> color = ghost
            9 -> color = steel
            10 -> color = fire
            11 -> color = water
            12 -> color = grass
            13 -> color = electric
            14 -> color = psychic
            15 -> color = ice
            16 -> color = dragon
            17 -> color = dark
            18 -> color = fairy
            else -> {
                color = colorPrimary
            }
        }
        return color
    }
}

