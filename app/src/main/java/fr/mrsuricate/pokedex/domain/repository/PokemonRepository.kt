package fr.mrsuricate.pokedex.domain.repository

import fr.mrsuricate.pokedex.domain.model.Pokemon

fun interface PokemonRepository {
    suspend fun getPokemonList(offset: Int): List<Pokemon>
}