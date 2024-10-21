package fr.mrsuricate.pokedex.ui.viewModel

import androidx.lifecycle.ViewModel
import fr.mrsuricate.pokedex.data.api.PokemonApi
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.data.api.model.Types

class DetailViewModel : ViewModel() {
    var pokemon: PokemonJsonModel = PokemonJsonModel()
        private set
    private val _types: MutableList<Types> = arrayListOf()
    val types: List<Types> = _types

    fun setPokemon(newPokemon: PokemonJsonModel) {
        pokemon = newPokemon
        getTypeLanguage()
    }

    private fun getTypeLanguage() {
        // Clear existing types
        _types.clear()

        // Iterate over the Pokémon types and fetch their translations
        pokemon.types.forEach { type ->
            val typeCall = PokemonApi.apiService.getType(name = type.type.name)
            PokemonApi.executeApiCall(typeCall, onSuccess = { response ->
                response.body()?.let { _types.add(it) }
            })
        }
    }
}