package fr.mrsuricate.pokedex.domain.model

import androidx.compose.ui.graphics.Color
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

class Type(
    var id: Int,
    val name: Map<String, String>,
) {
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