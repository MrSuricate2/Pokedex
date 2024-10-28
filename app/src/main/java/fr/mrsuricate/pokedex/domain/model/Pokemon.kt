package fr.mrsuricate.pokedex.domain.model

class Pokemon(
    val id: Int,
    val names: List<Name>,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val image: String,
    val types: List<Type>,
    val stats: List<Stats>
)