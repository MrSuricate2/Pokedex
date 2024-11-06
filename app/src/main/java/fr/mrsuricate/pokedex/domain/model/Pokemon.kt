package fr.mrsuricate.pokedex.domain.model

class Pokemon(
    val id: Int,
    val names: Map<String, String>,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val image: String,
    val types: List<Type>,
    val stats: Map<String, Int>,
)