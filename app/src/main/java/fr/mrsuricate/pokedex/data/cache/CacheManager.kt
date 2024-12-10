package fr.mrsuricate.pokedex.data.cache

class CacheManager(private val fileManager: FileManager) {

    fun <T> cacheValue(data: T, url: String) {
        if (url == "https://pokeapi.co/api/v2/pokemon") {
            fileManager.saveData(data.toString(), "pokemon.txt")
        } else if (url.startsWith("https://pokeapi.co/api/v2/pokemon/")) {
            val pokemon = url.replace("https://pokeapi.co/api/v2/pokemon/", "")
            fileManager.saveData(data.toString(), "pokemon_$pokemon.txt")
        } else if (url == "https://pokeapi.co/api/v2/language") {
            fileManager.saveData(data.toString(), "language.txt")
        }
    }

}
