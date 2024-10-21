package fr.mrsuricate.pokedex.data.api

import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.data.api.model.PokemonResponse
import fr.mrsuricate.pokedex.data.api.model.PokemonSpecies
import fr.mrsuricate.pokedex.data.api.model.Types
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int = 40,
        @Query("offset") offset: Int = 0,
    ): Call<PokemonResponse>

    @GET("pokemon/{name}")
    fun getPokemonInfo(
        @Path("name") name: String
    ): Call<PokemonJsonModel>

    @GET("pokemon-species/{id}")
    fun getSpecies(
        @Path("id") id: Int
    ): Call<PokemonSpecies>

    @GET("type/{name}")
    fun getType(
        @Path("name") name: String
    ): Call<Types>

    @GET("language")
    fun getLanguages(
    ): Call<PokemonResponse>

    @GET("language/{name}")
    fun getLanguage(
        @Path("name") name: String
    ): Call<Language>

}