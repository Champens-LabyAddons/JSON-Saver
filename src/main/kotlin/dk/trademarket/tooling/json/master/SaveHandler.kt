package dk.trademarket.tooling.json.master

import java.io.File

interface SaveHandler {
    fun getDefinedSaveFolder(): File
}