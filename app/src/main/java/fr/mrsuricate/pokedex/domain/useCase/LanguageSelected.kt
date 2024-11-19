package fr.mrsuricate.pokedex.domain.useCase

object LanguageSelected {
    private var LANGUAGE = "fr"

    fun getLanguage(): String {
        return LANGUAGE
    }

    fun setLanguage(language: String) {
        LANGUAGE = language
    }
}