package fr.mrsuricate.pokedex.domain.model

class Species(
    var id: Int,
    val name: String,
    val names: List<Name>,
)