package fr.mrsuricate.pokedex.ui.viewModel

import androidx.lifecycle.ViewModel
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.data.api.model.Types
import fr.mrsuricate.pokedex.domain.model.Pokemon

class DetailViewModel : ViewModel() {
    var pokemon: Pokemon = PokemonJsonModel().toDomain()
        private set
    private val _types: MutableList<Types> = arrayListOf()
    val types: List<Types> = _types

    fun setPokemon(newPokemon: Pokemon) {
        pokemon = newPokemon
    }
}