package fr.mrsuricate.pokedex.domain.repository

import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.data.api.model.PokemonType
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.model.Type
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PokemonRepositoryImpl(private val apiService: PokemonApiService) : PokemonRepository {


    //todo recontruire l'objet domaine
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
                                val image =
                                    pokemonResponse.body()?.sprites?.other?.dreamWorld?.frontDefault
                                        ?: pokemonResponse.body()?.sprites?.other?.officialArtwork?.frontDefault
                                        ?: ""
                                val stats = mutableMapOf<String, Int>()
                                pokemonResponse.body()?.stats?.map {
                                    stats.put(
                                        it.stat.name,
                                        it.baseStat
                                    )
                                }

                                Pokemon(
                                    id = pokemonResponse.body()?.id ?: 0,
                                    names = getPokemonNames(pokemonResponse.body()?.id ?: 0),
                                    baseExperience = pokemonResponse.body()?.baseExperience ?: 0,
                                    height = pokemonResponse.body()?.height ?: 0,
                                    weight = pokemonResponse.body()?.weight ?: 0,
                                    image = image,
                                    types = getPokemonTypes(
                                        pokemonResponse.body()?.types ?: emptyList()
                                    ),
                                    stats = stats
                                )

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


    override suspend fun getPokemonNames(id: Int): Map<String, String> {
        val response = apiService.getSpecies(id)

        val result = mutableMapOf<String, String>()

        if (response.isSuccessful) {
            val namesResults = response.body()?.names ?: emptyList()
            // Load details in parallel and filter out `nulls
            coroutineScope {
                namesResults.map { names ->
                    result.put(
                        names.language.name,
                        names.name
                    )
                }
            }
            return result
        } else {
            return emptyMap() // Returns an empty list in the event of a network error
        }
    }

    override suspend fun getPokemonTypes(types: List<PokemonType>): List<Type> {
        val listTypes: MutableList<Type> = mutableListOf()
        types.map { type ->
            val response = apiService.getType(type.type.name)
            coroutineScope {
                if (response.isSuccessful) {
                    val listname = mutableMapOf<String, String>()
                    response.body()?.names?.map {
                        listname.put(
                            it.language.name,
                            it.name
                        )
                    }
                    listTypes.add(
                        Type(
                            id = response.body()?.id ?: 0,
                            name = listname
                        )
                    )
                } else {
                    return@coroutineScope
                }
            }
        }
        return listTypes
    }

}