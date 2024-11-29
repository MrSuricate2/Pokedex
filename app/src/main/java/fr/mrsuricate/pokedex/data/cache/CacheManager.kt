package fr.mrsuricate.pokedex.data.cache

class CacheManager(private val fileManager: FileManager) {

    fun getCache(fileName: String): String {
        return fileManager.loadData(fileName)
    }

    fun setCache(data: String, fileName: String) {
        fileManager.saveData(data, fileName)
    }

}
