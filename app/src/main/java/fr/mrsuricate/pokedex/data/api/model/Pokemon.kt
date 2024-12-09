package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable