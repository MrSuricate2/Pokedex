package fr.mrsuricate.pokedex.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mrsuricate.pokedex.data.api.PokemonApi
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.ui.navigation.SettingLanguage
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    private var _settings: MutableMap<String, String> = mutableMapOf()
    val settings: Map<String, String> = _settings

    private var _language: MutableMap<String, Language> =
        mutableMapOf()
    val language: Map<String, Language> = _language

    private val _selectedLanguage: MutableLiveData<Language> =
        MutableLiveData(
            Language()
        )
    val selectedLanguage: LiveData<Language> =
        _selectedLanguage

    init {
        _settings.put("Langue", SettingLanguage.route)
        getLanguage()
    }

    private fun getLanguage() {
        viewModelScope.launch {
            getLanguageResult()
        }
    }

    private fun getLanguageResult() {
        _language.clear()
        val listResult = PokemonApi.apiService.getLanguages()
        PokemonApi.executeApiCall(listResult, onSuccess = { response ->
            response.body()?.let { languageResponse ->
                languageResponse.results.forEach { language ->
                    val listLanguage = PokemonApi.apiService.getLanguage(name = language.name)
                    PokemonApi.executeApiCall(listLanguage, onSuccess = { response1 ->
                        response1.body()?.names?.forEach { name ->
                            if (name.language.name == selectedLanguage.value?.name) {
                                response1.body()?.let { _language.put(it.name, response1.body()!!) }
                            }
                        }
                    })
                }
            }
        })
    }

    fun changeSelectedLangue(lang: Language) {
        _selectedLanguage.value = lang
    }
}