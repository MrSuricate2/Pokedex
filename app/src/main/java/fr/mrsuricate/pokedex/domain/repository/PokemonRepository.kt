package fr.mrsuricate.pokedex.domain.repository

import fr.mrsuricate.pokedex.data.api.model.PokemonType
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.model.Type

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int): List<Pokemon>
    suspend fun getPokemonNames(id: Int): Map<String, String>
    suspend fun getPokemonTypes(types: List<PokemonType>): List<Type>

    suspend fun getPokemonFromCache(): List<Pokemon>
    suspend fun cachePokemonList(pokemonList: List<Pokemon>)
}