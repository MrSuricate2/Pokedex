package fr.mrsuricate.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = String(),
    @SerializedName("order") val order: Int? = null,
    @SerializedName("gender_rate") val genderRate: Int? = null,
    @SerializedName("capture_rate") val captureRate: Int? = null,
    @SerializedName("base_happiness") val baseHappiness: Int? = null,
    @SerializedName("is_baby") val isBaby: Boolean? = null,
    @SerializedName("is_legendary") val isLegendary: Boolean? = null,
    @SerializedName("is_mythical") val isMythical: Boolean? = null,
    @SerializedName("hatch_counter") val hatchCounter: Int? = null,
    @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean? = null,
    @SerializedName("forms_switchable") val formsSwitchable: Boolean? = null,
    @SerializedName("growth_rate") val growthRate: NamedApiResource? = null,
    @SerializedName("pokedex_numbers") val pokedexNumbers: List<PokemonSpeciesDexEntry?>? = null,
    @SerializedName("egg_groups") val eggGroups: List<NamedApiResource?>? = null,
    @SerializedName("color") val color: NamedApiResource? = null,
    @SerializedName("shape") val shape: NamedApiResource? = null,
    @SerializedName("evolves_from_species") val evolvesFromSpecies: NamedApiResource? = null,
    @SerializedName("evolution_chain") val evolutionChain: APIResource? = null,
    @SerializedName("habitat") val habitat: NamedApiResource? = null,
    @SerializedName("generation") val generation: NamedApiResource? = null,
    @SerializedName("names") val names: List<Name> = listOf(),
    @SerializedName("pal_park_encounters") val palParkEncounters: List<PalParkEncounterArea?>? = null,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorText?>? = null,
    @SerializedName("form_descriptions") val formDescriptions: List<Description?>? = null,
    @SerializedName("genera") val genera: List<Genus?>? = null,
    @SerializedName("varieties") val varieties: List<PokemonSpeciesVariety?>? = null,
) {
    fun toDomain(): List<fr.mrsuricate.pokedex.domain.model.Name> {
        val names: MutableList<fr.mrsuricate.pokedex.domain.model.Name> = mutableListOf()
        this.names.forEach { name ->
            names.add(name.toDomain())
        }
        return names
    }
}