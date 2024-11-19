package fr.mrsuricate.pokedex.domain.repository

import fr.mrsuricate.pokedex.domain.model.Language

interface LanguageRepository {
    suspend fun getLanguageList(): List<Language>
    fun getLanguage(): String
    fun setLanguage(language: String)
}