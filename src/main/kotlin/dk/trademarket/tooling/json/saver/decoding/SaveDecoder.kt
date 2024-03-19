package dk.trademarket.tooling.json.saver.decoding

import dk.trademarket.tooling.json.SaveHandler
import java.io.File

/**
 * Abstract class for decoding save files
 * @param saveFile The save file to decode
 */
abstract class SaveDecoder(protected val saveFile: File) : SaveHandler {
    abstract fun decode(): Any
}