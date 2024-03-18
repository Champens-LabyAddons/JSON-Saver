package dk.trademarket.tooling.json.encoding

import dk.trademarket.tooling.json.master.SaveHandler
import java.io.File

/**
 * SaveEncoder is an abstract class that is used to encode objects, classes, collections and maps to a json file.
 * The class is abstract, and the listed functions are to be implemented by the inheriting class.
 *
 * @property type is the object, class, collection or map that is to be encoded
 * @property saveFolder is the folder where the json file is to be saved
 */
abstract class SaveEncoder(protected var type: Any, protected var saveFolder: File) : SaveHandler {
    init {
        this.saveFolder.mkdirs()
    }

    abstract fun encode(fileName: String)
}