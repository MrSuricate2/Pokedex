package fr.mrsuricate.pokedex.domain.repository

import android.util.Log
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
                Log.d("PokemonRepositoryImpl", pokemonResults.size.toString())
                // Charger les détails en parallèle et filtrer les `null`
                coroutineScope {
                    pokemonResults.map { pokemonSummary ->
                        async {
                            val pokemonResponse = apiService.getPokemonInfo(pokemonSummary.name)
                            if (pokemonResponse.body()?.isDefault == true) {
                                pokemonResponse.body()
                                    ?.toDomain() // Renvoie `null` si l'appel échoue ou le corps est vide
                            } else {
                                null
                            }
                        }
                    }.mapNotNull { it.await() } // Filtre les valeurs `null` résultantes
                }
            } else {
                emptyList() // Retourne une liste vide en cas d'erreur réseau
            }
        } catch (e: Exception) {
            emptyList() // Gestion d'exception réseau
        }
    }


}