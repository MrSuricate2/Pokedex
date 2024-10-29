package fr.mrsuricate.pokedex.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mrsuricate.pokedex.data.api.model.PokemonResponse
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PokemonRepository) : ViewModel() {

    // MutableLiveData pour stocker la liste des Pokémon
    private val _pokemonList = mutableListOf<Pokemon>()

    // MutableStateFlow pour exposer la liste des Pokémon
    private val _pokemonFlow = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonFlow: StateFlow<List<Pokemon>> = _pokemonFlow

    data class UiState(
        var pokemonResponse: PokemonResponse = PokemonResponse(),
        var pokemons: List<Pokemon> = ArrayList(),
        var pokemonDisplay: Int = 0
    )

    private val _pokemonData = MutableStateFlow(UiState())
    val pokemonData: StateFlow<UiState> = _pokemonData.asStateFlow()

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
                // Ajoute les nouveaux Pokémon à la liste existante
                _pokemonList.addAll(pokemonList)
                // Met à jour le StateFlow
                _pokemonFlow.value = _pokemonList.toList()
                Log.d(
                    "HomeViewModel",
                    "Nouvelle liste de Pokémon chargée, taille actuelle: ${_pokemonList.size}"
                )
            }
        } catch (_: Exception) {

        }
    }

    // Centralized state update function
    private fun updateState(update: UiState.() -> UiState) {
        _pokemonData.value = _pokemonData.value.update()
    }

//    private fun getPokemonResult(offset: Int = 0) {
//        val listResult = PokemonApi.apiService.getPokemonList(offset = offset, limit = 100)
//        PokemonApi.executeApiCall(listResult, onSuccess = { response ->
//            response.body()?.let { pokemonResponse ->
//                updateState {
//                    copy(
//                        pokemonResponse = pokemonResponse,
//                        pokemonDisplay = offset + 100
//                    )
//                }
//                pokemonResponse.results.forEach {
//                    getPokemonList(name = it.name)
//                }
//            }
//        })
//    }
//
//    private fun getPokemonList(name: String) {
//        val listPokemon = PokemonApi.apiService.getPokemonInfo(name = name)
//        PokemonApi.executeApiCall(listPokemon, onSuccess = { response ->
//            response.body()?.let { pokemon ->
//                if (pokemon.isDefault) {
//                    updateState {
//                        // Ajoute le nouveau Pokémon à la liste existante
//                        val updatedPokemons =
//                            pokemons.toMutableList().apply { add(pokemon.toDomain()) }
//                        copy(pokemons = updatedPokemons.sortedBy { it.id })
//                    }
//                }
//            }
//        })
//    }


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