package fr.mrsuricate.pokedex.data.cache

class CacheManager(private val fileManager: FileManager) {

    fun <T> cacheValue(data: T, url: String) {
        var fileName = ""
        when {
            url == "https://pokeapi.co/api/v2/pokemon" -> {
                fileName = "pokemon.txt"
            }

            url.startsWith("https://pokeapi.co/api/v2/pokemon/") -> {
                val pokemon = url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                fileName = "pokemon_$pokemon.txt"
            }

            url == "https://pokeapi.co/api/v2/language" -> {
                fileName = "language.txt"
            }
        }
        fileManager.saveData(data.toString(), fileName)
    }

}
