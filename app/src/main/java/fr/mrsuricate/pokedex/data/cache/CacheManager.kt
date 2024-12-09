package fr.mrsuricate.pokedex.data.cache

class CacheManager(private val fileManager: FileManager) {

    fun cacheValue(data: Any, url: String) {
        when (url) {
            "https://pokeapi.co/api/v2/pokemon" -> fileManager.saveData(
                data.toString(),
                "pokemons.txt"
            )

            "https://pokeapi.co/api/v2/language" -> fileManager.saveData(
                data.toString(),
                "language.txt"
            )
        }
    }

}
