package fr.mrsuricate.pokedex.data.cache

import android.content.Context
import java.io.File

class FileManager(private val context: Context) {

    fun saveData(data: String, fileName: String) {
        val file = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        file.write(data.toByteArray())
        file.close()
    }

    fun loadData(fileName: String): String? {
        return try {
            if (!fileExists(fileName)) {
                return null
            }
            val file = context.openFileInput(fileName)
            val data = file.bufferedReader().use { it.readText() }
            file.close()
            data
        } catch (e: Exception) {
            null
        }
    }

    fun fileExists(fileName: String): Boolean {
        val file = File(context.filesDir, fileName)
        return file.exists()
    }

    fun deleteFile(fileName: String): Boolean {
        return context.deleteFile(fileName)
    }
}
