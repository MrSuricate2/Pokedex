package fr.mrsuricate.pokedex.domain.repository

import android.util.Log
import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.data.api.model.PokemonType
import fr.mrsuricate.pokedex.data.cache.CacheManager
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.model.Type

class PokemonRepositoryImpl(
    private val apiService: PokemonApiService,
    private val cacheManager: CacheManager
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int): List<Pokemon> {
        return try {
            val response = apiService.getPokemonList(offset = offset)
            if (response.isSuccessful) {
                Log.d("PokemonRepositoryImpl", "response: ${response.isSuccessful}")
                val pokemonResults = response.body()?.results ?: emptyList()

                // Process each Pokémon result synchronously
                pokemonResults.mapNotNull { pokemonSummary ->
                    val pokemonResponse = apiService.getPokemonInfo(pokemonSummary.name)
                    val body = pokemonResponse.body()

                    if (pokemonResponse.isSuccessful && body?.isDefault == true) {
                        val image = body.sprites.other.dreamWorld.frontDefault
                            ?: body.sprites.other.officialArtwork.frontDefault

                        val stats = body.stats.associate { it.stat.name to it.baseStat }

                        Pokemon(
                            id = body.id,
                            names = getPokemonNames(body.id),
                            baseExperience = body.baseExperience,
                            height = body.height,
                            weight = body.weight,
                            image = image,
                            types = getPokemonTypes(body.types),
                            stats = stats
                        )
                    } else {
                        null // Exclude any unsuccessful or non-default Pokémon
                    }
                }
            } else {
                emptyList() // Returns an empty list if the initial API call fails
            }
        } catch (e: Exception) {
            Log.e("PokemonRepositoryImpl", "Error fetching Pokemon list", e)
            emptyList() // Returns an empty list in case of an exception
        }
    }
    override suspend fun getPokemonNames(id: Int): Map<String, String> {
        val response = apiService.getSpecies(id)

        return response.body()?.names
            ?.associate { names -> names.language.name to names.name } // Create a map directly
            ?: emptyMap() // In case names list is null
    }

    override suspend fun getPokemonTypes(types: List<PokemonType>): List<Type> {
        return types.map { type ->
            val response = apiService.getType(type.type.name)

            val namesMap = response.body()?.names
                ?.associate { it.language.name to it.name }
                ?: emptyMap()

            Type(
                id = response.body()?.id ?: 0,
                name = namesMap
            )
        }
    }

    override suspend fun getPokemonFromCache(): List<Pokemon> {
        return try {
            val url = "https://pokeapi.co/api/v2/pokemon"

            // Check if cache is available first
            if (!cacheManager.isCacheAvailable(url)) {
                Log.d("PokemonRepositoryImpl", "No cache available for Pokemon list")
                return emptyList()
            }

            val cachedData = cacheManager.retrieveValue(
                url = url,
                data = Array<Pokemon>::class.java
            )

            if (cachedData != null) {
                Log.d(
                    "PokemonRepositoryImpl",
                    "Successfully loaded ${cachedData.size} Pokemon from cache"
                )
                cachedData.toList()
            } else {
                Log.d("PokemonRepositoryImpl", "Cache file exists but data is null")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("PokemonRepositoryImpl", "Error reading Pokemon cache", e)
            emptyList()
        }
    }

    override suspend fun cachePokemonList(pokemonList: List<Pokemon>) {
        try {
            val success = cacheManager.cacheValue(
                data = pokemonList.toTypedArray(),
                url = "https://pokeapi.co/api/v2/pokemon"
            )
            if (success) {
                Log.d("PokemonRepositoryImpl", "Successfully cached ${pokemonList.size} Pokemon")
            } else {
                Log.w("PokemonRepositoryImpl", "Failed to cache Pokemon list")
            }
        } catch (e: Exception) {
            Log.e("PokemonRepositoryImpl", "Error caching Pokemon list", e)
        }
    }

}