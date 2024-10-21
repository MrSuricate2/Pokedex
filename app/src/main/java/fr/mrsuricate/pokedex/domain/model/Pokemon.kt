package fr.mrsuricate.pokedex.domain.model

class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val weight: Int,
    val species: Species
)