package fr.mrsuricate.pokedex.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mrsuricate.pokedex.domain.model.Language
import fr.mrsuricate.pokedex.domain.useCase.LanguageManager
import fr.mrsuricate.pokedex.ui.navigation.SettingClearCache
import fr.mrsuricate.pokedex.ui.navigation.SettingLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    private val languageManager: LanguageManager
) : ViewModel() {

    private var _settings: MutableMap<String, String> = mutableMapOf()
    val settings: Map<String, String> = _settings

    private var _language: MutableList<Language> = mutableListOf()
    private val _languageFlow = MutableStateFlow<List<Language>>(emptyList())
    val languageFlow: StateFlow<List<Language>> = _languageFlow

    init {
        _settings["Langue"] = SettingLanguage.route
        _settings["Vider le cache"] = SettingClearCache.route
        getLanguage()
    }

    private fun getLanguage() {
        viewModelScope.launch {
            val languageList = languageManager.getAllLanguage()
            if (languageList.isNotEmpty()) {
                _language.addAll(languageList)
                _languageFlow.value = _language.toList()
            }
        }
    }

    fun getSelectedLangue(): String {
        return this.languageManager.getLanguage()
    }

    fun setSelectedLangue(lang: String) {
        this.languageManager.setLanguage(lang)
    }

    fun clearCache() {
        languageManager.clearCache()
    }
}