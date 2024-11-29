package fr.mrsuricate.pokedex.domain.useCase

class LanguageManager {
    private var LANGUAGE = "fr"

    fun getLanguage(): String {
        return LANGUAGE
    }

    fun setLanguage(language: String) {
        LANGUAGE = language
    }

    //todo Ajouter içi l'appel du repo pour retourner la liste des langues
}