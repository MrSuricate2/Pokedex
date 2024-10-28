package fr.mrsuricate.pokedex.data.api.model

import com.google.gson.annotations.SerializedName
import fr.mrsuricate.pokedex.data.api.PokemonApi
import fr.mrsuricate.pokedex.domain.model.Name
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.domain.model.Stats
import fr.mrsuricate.pokedex.domain.model.Type

data class PokemonJsonModel(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = String(),
    @SerializedName("base_experience") var baseExperience: Int = 0,
    @SerializedName("height") var height: Int = 0,
    @SerializedName("is_default") var isDefault: Boolean = false,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("weight") var weight: Int = 0,
    @SerializedName("abilities") var abilities: ArrayList<PokemonAbility> = arrayListOf(),
    @SerializedName("forms") var forms: ArrayList<NamedApiResource> = arrayListOf(),
    @SerializedName("game_indices") var gameIndices: ArrayList<VersionGameIndex> = arrayListOf(),
    @SerializedName("held_items") var heldItems: ArrayList<PokemonHeldItem> = arrayListOf(),
    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerializedName("moves") var moves: ArrayList<PokemonMove> = arrayListOf(),
    @SerializedName("past_types") var pastTypes: ArrayList<PokemonTypePast> = arrayListOf(),
    @SerializedName("sprites") var sprites: PokemonSprites = PokemonSprites(),
    @SerializedName("cries") var cries: PokemonCries? = PokemonCries(),
    @SerializedName("species") var species: NamedApiResource = NamedApiResource(),
    @SerializedName("stats") var stats: ArrayList<PokemonStat> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<PokemonType> = arrayListOf(),
    @SerializedName("past_abilities") var pastAbilities: ArrayList<PastAbilitiesItem> = arrayListOf(),
) {

    fun toDomain(): Pokemon {
        return Pokemon(
            id = this.id,
            names = this.getNames(),
            baseExperience = this.baseExperience,
            height = this.height,
            weight = this.weight,
            image = this.getSprites(),
            types = this.getTypes(),
            stats = this.getStats()
        )
    }

    private fun getNames(): List<Name> {
        val speciesResponse = PokemonApi.apiService.getSpecies(this.id)
        val listNames: MutableList<Name> = mutableListOf()
        PokemonApi.executeApiCall(speciesResponse, onSuccess = {
            it.body()?.let { it1 -> listNames.addAll(it1.toDomain()) }
        })
        return listNames
    }

    private fun getSprites(): String {
        return this.sprites.other.dreamWorld.frontDefault ?: ""
    }

    private fun getTypes(): List<Type> {
        val listTypes: MutableList<Type> = mutableListOf()
        this.types.forEach { type ->
            val typeCall = PokemonApi.apiService.getType(name = type.type.name)
            PokemonApi.executeApiCall(typeCall, onSuccess = { response ->
                response.body()?.let {
                    listTypes.add(it.toDomain())
                }
            })
        }
        return listTypes
    }

    private fun getStats(): List<Stats> {
        val listStats: MutableList<Stats> = mutableListOf()
        this.stats.forEach { stat ->
            listStats.add(stat.toDomain())
        }
        return listStats
    }
}