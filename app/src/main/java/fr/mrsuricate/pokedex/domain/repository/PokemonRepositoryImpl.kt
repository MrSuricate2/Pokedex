package fr.mrsuricate.pokedex.domain.repository

import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.domain.model.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PokemonRepositoryImpl(private val apiService: PokemonApiService) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int): List<Pokemon> {
        return try {
            val response = apiService.getPokemonList(offset = offset)
            if (response.isSuccessful) {
                val pokemonResults = response.body()?.results ?: emptyList()
                // Load details in parallel and filter out `nulls
                coroutineScope {
                    pokemonResults.map { pokemonSummary ->
                        async {
                            val pokemonResponse = apiService.getPokemonInfo(pokemonSummary.name)
                            if (pokemonResponse.body()?.isDefault == true) {
                                pokemonResponse.body()
                                    ?.toDomain() // Returns `null` if the call fails or the body is empty
                            } else {
                                null
                            }
                        }
                    }.mapNotNull { it.await() } // Filters out the resulting `null` values
                }
            } else {
                emptyList() // Returns an empty list in the event of a network error
            }
        } catch (e: Exception) {
            emptyList() // Network exception management
        }
    }


}