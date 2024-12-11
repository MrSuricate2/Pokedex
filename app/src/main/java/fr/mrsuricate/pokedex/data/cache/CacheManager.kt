package fr.mrsuricate.pokedex.data.cache

import com.google.gson.Gson

class CacheManager(private val fileManager: FileManager) {

    private fun getFileNameFromUrl(url: String): String {
        return when {
            url == "https://pokeapi.co/api/v2/pokemon" -> "pokemon.txt"
            url.startsWith("https://pokeapi.co/api/v2/pokemon/") -> {
                val pokemon = url.removePrefix("https://pokeapi.co/api/v2/pokemon/")
                "pokemon_$pokemon.txt"
            }

            url == "https://pokeapi.co/api/v2/language" -> "language.txt"
            else -> throw IllegalArgumentException("URL invalid: $url")
        }
    }

    fun <T> cacheValue(data: T, url: String) {
        val fileName = getFileNameFromUrl(url)
        val jsonData = Gson().toJson(data)
        fileManager.saveData(jsonData, fileName)
    }

    fun <T> retrieveValue(url: String, data: Class<T>): T {
        val fileName = getFileNameFromUrl(url)
        val jsonData = fileManager.loadData(fileName)
        return Gson().fromJson(jsonData, data)
    }

}
