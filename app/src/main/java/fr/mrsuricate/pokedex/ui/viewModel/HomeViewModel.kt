package fr.mrsuricate.pokedex.ui.viewModel

import android.util.Log
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

    init {
        retrieveAgentsData()
    }

    private fun retrieveAgentsData() {
        viewModelScope.launch {
            loadPokemonList()
        }
    }

    private suspend fun loadPokemonList(offset: Int = 0) {
        try {
            val pokemonList = repository.getPokemonList(offset)
            if (pokemonList.isNotEmpty()) {
                // Adds new Pokémon to the existing list
                _pokemonList.addAll(pokemonList)
                // Updates StateFlow
                _pokemonFlow.value = _pokemonList.toList()
                Log.d(
                    "HomeViewModel",
                    "Nouvelle liste de Pokémon chargée, taille actuelle: ${_pokemonList.size}"
                )
            }
        } catch (_: Exception) {

        }
    }


    fun addPokemonToList() {
        viewModelScope.launch {
//            val urlString = pokemonData.value.pokemonResponse.next ?: return@launch
//            val uri = Uri.parse(urlString)
//            val offset = uri.getQueryParameter("offset")?.toIntOrNull() ?: 0
            Log.d(
                "HomeViewModel",
                "salut je demande plus de pokemon offset de ${_pokemonList.size}"
            )
            loadPokemonList(offset = _pokemonList.size)
        }
    }
}