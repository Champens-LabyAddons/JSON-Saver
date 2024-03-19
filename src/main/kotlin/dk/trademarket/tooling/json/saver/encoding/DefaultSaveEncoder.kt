package dk.trademarket.tooling.json.saver.encoding

import com.google.gson.Gson
import java.io.File

/**
 * DefaultSaveEncoder is a SaveEncoder that uses the Gson library to encode the type to a JSON string and then writes it to a file.
 * @param type The type to encode
 * @param saveFolder The folder to save the file in
 * @see SaveEncoder
 */
class DefaultSaveEncoder(type: Any, saveFolder: File) : SaveEncoder(type, saveFolder) {
    private val gson: Gson = Gson()

    /**
     * Encodes the type to a JSON string and writes it to a file.
     * @param fileName The name of the file to save the JSON string to
     */
    override fun encode(fileName: String) {
        val jsonString: String = gson.toJson(type)
        val file = File(saveFolder, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        println(jsonString)
        file.writeText(jsonString)
    }

    override fun getDefinedSaveFolder(): File {
        return saveFolder
    }
}