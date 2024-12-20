package fr.mrsuricate.pokedex.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PokemonRepository) : ViewModel() {

    // MutableLiveData to store the list of Pokémon
    private val _pokemonList = mutableListOf<Pokemon>()

    // MutableStateFlow to display the list of Pokémon
    private val _pokemonFlow = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonFlow: StateFlow<List<Pokemon>> = _pokemonFlow

    // LiveData to control the visibility of the "LandingScreen"
    private val _showHomeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val showHomeScreen: LiveData<Boolean> = _showHomeScreen


    fun setShowHomeScreen(value: Boolean) {
        _showHomeScreen.value = value
    }

    init {
        retrieveAgentsData()
    }

    private fun retrieveAgentsData() {
        viewModelScope.launch {
            loadPokemonList()
        }
    }

    private suspend fun loadPokemonList(offset: Int = 0) {
        val pokemonList = repository.getPokemonList(offset)
        Log.d("HomeViewModel", "loadPokemonList: ${pokemonList.size}")
        if (pokemonList.isNotEmpty()) {
            // Adds new Pokémon to the existing list
            _pokemonList.addAll(pokemonList)
            // Updates StateFlow
            _pokemonFlow.value = _pokemonList.toList()
        }
    }


    fun addPokemonToList() {
        viewModelScope.launch {
            loadPokemonList(offset = _pokemonList.size)
        }
    }
}