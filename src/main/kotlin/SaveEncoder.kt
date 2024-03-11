package com.github.wildtooth.tooling

import java.io.File
import java.lang.reflect.Field

class SaveEncoder: SaveHandler {
    private var type: Any
    private var saveFolder: File

    constructor(type: Class<*>, saveFolder: File) {
        this.type = type
        this.saveFolder = saveFolder
    }

    constructor(type: Collection<*>, saveFolder: File) {
        this.type = type
        this.saveFolder = saveFolder
    }

    constructor(type: Map<*, *>, saveFolder: File) {
        this.type = type
        this.saveFolder = saveFolder
    }

    override fun getSaveFolder(): File {
        TODO("Not yet implemented")
    }

    fun encode() {
        when (type) {
            is Class<*> -> classEncoder()
            is Collection<*> -> collectionEncoder()
            is Map<*, *> -> mapEncoder()
        }
    }

    private fun classEncoder() {
        val instance = (type as Class<*>).getDeclaredConstructor().newInstance()
        val fields: Array<out Field> = (type as Class<*>).declaredFields

        for (field in fields) {
            println(field.name)
            val b = field.canAccess(instance)
            field.isAccessible = true
            println(b)
            println(field.get(instance))
        }
    }

    private fun collectionEncoder() {
        TODO("Not yet implemented")
    }

    private fun mapEncoder() {
        TODO("Not yet implemented")
    }
}