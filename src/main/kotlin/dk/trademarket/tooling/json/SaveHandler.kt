package dk.trademarket.tooling.json

import java.io.File

interface SaveHandler {
    fun getDefinedSaveFolder(): File
}