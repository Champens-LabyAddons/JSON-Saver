package dk.trademarket.tooling.json.decoding

import com.google.gson.Gson
import dk.trademarket.tooling.json.decoding.SaveDecoder
import java.io.File
import java.lang.reflect.Type

/**
 * Default implementation of SaveDecoder
 * @param saveFile The save file to decode
 * @param typeOfT The type of the object to decode
 */
class DefaultSaveDecoder(saveFile: File, private val typeOfT: Type): SaveDecoder(saveFile) {
    private val gson = Gson()
    /**
     * Decodes the save file
     * @return The decoded object
     */
    override fun decode(): Any {
        val json = saveFile.readText()
        return gson.fromJson(json, typeOfT)
    }

    override fun getDefinedSaveFolder(): File {
        return saveFile
    }
}