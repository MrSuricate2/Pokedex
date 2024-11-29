package fr.mrsuricate.pokedex.data.cache

import android.content.Context

class FileManager(private val context: Context) {

    fun saveData(data: String, fileName: String) {
        val file = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        file.write(data.toByteArray())
        file.close()
    }

    fun loadData(fileName: String): String {
        val file = context.openFileInput(fileName)
        val data = file.bufferedReader().use { it.readText() }
        file.close()
        return data
    }
}
