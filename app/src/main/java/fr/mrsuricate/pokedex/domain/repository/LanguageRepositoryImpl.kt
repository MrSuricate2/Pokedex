package fr.mrsuricate.pokedex.domain.repository

import android.util.Log
import fr.mrsuricate.pokedex.data.api.PokemonApiService
import fr.mrsuricate.pokedex.domain.model.Language
import fr.mrsuricate.pokedex.domain.useCase.LanguageSelected
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class LanguageRepositoryImpl(
    private val apiService: PokemonApiService,
    private val language: LanguageSelected
) : LanguageRepository {

    override suspend fun getLanguageList(): List<Language> {
        return try {
            val response = apiService.getLanguages()
            Log.d("LanguageRepositoryImpl", response.isSuccessful.toString())
            if (response.isSuccessful) {
                val languagesResults = response.body()?.results ?: emptyList()
                Log.d("LanguageRepositoryImpl", languagesResults.size.toString())
                coroutineScope {
                    languagesResults.map { languagesSummary ->
                        async {
                            val languageResponse = apiService.getLanguage(languagesSummary.name)
                            if (languageResponse.isSuccessful) {
                                languageResponse.body()?.toDomain()
                            } else {
                                null
                            }
                        }
                    }.mapNotNull { it.await() }
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getLanguage(): String {
        return language.getLanguage()
    }

    override fun setLanguage(language: String) {
        this.language.setLanguage(language)
    }
}