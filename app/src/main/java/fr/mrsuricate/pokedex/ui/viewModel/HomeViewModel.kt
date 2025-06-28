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

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Track if initial data was loaded from cache
    private var isDataLoadedFromCache = false

    fun setShowHomeScreen(value: Boolean) {
        _showHomeScreen.value = value
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // Try to load from cache first
                val cachedData = repository.getPokemonFromCache()
                if (cachedData.isNotEmpty()) {
                    Log.d("HomeViewModel", "Loaded ${cachedData.size} Pokémon from cache")
                    _pokemonList.addAll(cachedData)
                    _pokemonFlow.value = _pokemonList.toList()
                    isDataLoadedFromCache = true
                } else {
                    // If no cache, load from network
                    Log.d("HomeViewModel", "No cache found, loading from network")
                    loadPokemonFromNetwork(0)
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading initial data", e)
                // If cache fails, try network
                _error.value = "Error loading data: ${e.message}"
                loadPokemonFromNetwork(0)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun loadPokemonFromNetwork(offset: Int) {
        try {
            val pokemonList = repository.getPokemonList(offset)
            Log.d("HomeViewModel", "Loaded ${pokemonList.size} Pokémon from network")
            if (pokemonList.isNotEmpty()) {
                if (offset == 0) {
                    // First load, replace cache
                    _pokemonList.clear()
                    _pokemonList.addAll(pokemonList)
                    // Save to cache
                    repository.cachePokemonList(pokemonList)
                } else {
                    // Load more, append to existing list
                    _pokemonList.addAll(pokemonList)
                }
                _pokemonFlow.value = _pokemonList.toList()
                _error.value = null
            } else {
                if (offset == 0) {
                    _error.value = "No Pokémon data available"
                }
            }
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error loading Pokémon from network", e)
            if (offset == 0) {
                _error.value = "Network error: ${e.message}"
            }
        }
    }

    // Method to refresh data (pull to refresh)
    fun refreshData() {
        viewModelScope.launch {
            if (_isLoading.value) {
                Log.d("HomeViewModel", "Already loading, ignoring refresh request")
                return@launch
            }
            _isLoading.value = true
            _error.value = null
            try {
                loadPokemonFromNetwork(0)
            } catch (e: Exception) {
                _error.value = "Refresh failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Method to load more Pokémon (pagination)
    fun loadMorePokemon() {
        if (_isLoading.value) {
            Log.d("HomeViewModel", "Already loading, ignoring load more request")
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                loadPokemonFromNetwork(_pokemonList.size)
            } catch (e: Exception) {
                _error.value = "Failed to load more: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}