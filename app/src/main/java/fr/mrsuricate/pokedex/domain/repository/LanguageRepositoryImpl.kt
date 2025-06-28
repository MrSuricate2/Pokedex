package fr.mrsuricate.pokedex.domain.repository

import android.util.Log
import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.data.cache.CacheManager
import fr.mrsuricate.pokedex.domain.model.Language


class LanguageRepositoryImpl(
    private val apiService: PokemonApiService,
    private val cacheManager: CacheManager
) : BaseRepository<Language>(cacheManager), LanguageRepository {

    override suspend fun getLanguageList(): List<Language> {
        val cacheUrl = "https://pokeapi.co/api/v2/language"

        // Retrieves languages from cache
        val cachedLanguages: List<Language>? = fetchFromCache(cacheUrl, Array<Language>::class.java)

        if (!cachedLanguages.isNullOrEmpty()) {
            Log.d("LanguageRepositoryImpl", "Data retrieved from cache. (${cachedLanguages.size})")
            return cachedLanguages
        }

        // Retrieves languages from API
        val languages = fetchFromApi()
        if (languages.isNotEmpty()) {
            updateCache(cacheUrl, languages)
        }
        return languages

    }

    override fun clearCache() {
        cacheManager.clearCache("https://pokeapi.co/api/v2/language")
    }

    override suspend fun fetchFromApi(vararg params: Any): List<Language> {
        return try {
            val response = apiService.getLanguages()
            if (response.isSuccessful) {
                val languageResults = response.body()?.results ?: emptyList()

                languageResults.mapNotNull { languageSummary ->
                    val languageResponse = apiService.getLanguage(languageSummary.name)
                    if (languageResponse.isSuccessful) {
                        languageResponse.body()?.toDomain()?.also {
                            updateCache(
                                "https://pokeapi.co/api/v2/language/${languageSummary.name}",
                                it
                            )
                        }
                    } else {
                        null
                    }
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("LanguageRepositoryImpl", "Error retrieving languages from API: ${e.message}")
            emptyList()
        }
    }
}