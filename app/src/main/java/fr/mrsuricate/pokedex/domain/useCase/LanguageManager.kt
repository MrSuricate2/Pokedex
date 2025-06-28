package fr.mrsuricate.pokedex.domain.useCase

import fr.mrsuricate.pokedex.data.cache.CacheManager
import fr.mrsuricate.pokedex.domain.model.Language
import fr.mrsuricate.pokedex.domain.repository.LanguageRepository

class LanguageManager(
    private val languageRepository: LanguageRepository,
    private val cacheManager: CacheManager
) {
    private var LANGUAGE = "fr"

    fun getLanguage(): String {
        return try {
            val cachedLanguage = cacheManager.retrieveValue("locale", String::class.java)
            cachedLanguage ?: LANGUAGE
        } catch (e: Exception) {
            LANGUAGE
        }
    }

    fun setLanguage(language: String) {
        LANGUAGE = language
        cacheManager.cacheValue(language, "locale")
    }

    suspend fun getAllLanguage(): List<Language> {
        return languageRepository.getLanguageList()
    }

    fun clearCache() {
        languageRepository.clearCache()
    }
}