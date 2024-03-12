package com.github.wildtooth.tooling

import com.github.wildtooth.tooling.json.JsonArray
import com.github.wildtooth.tooling.json.JsonObject
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

    fun encode(fileName: String) {
        val jsonObject: JsonObject = when (type) {
            is Class<*> -> classEncoder()
            is Collection<*> -> collectionEncoder()
            is Map<*, *> -> mapEncoder()
            else -> {
                throw IllegalArgumentException("Type not supported")
            }
        }
        val encode: String = jsonObject.encode()
        println(encode)
        println(jsonObject.decode(encode).encode())
    }

    private fun classEncoder(): JsonObject {
        val jsonObject = JsonObject()
        val instance = (type as Class<*>).getDeclaredConstructor().newInstance()
        val fields: Array<out Field> = (type as Class<*>).declaredFields

        for (field in fields) {
            val b = field.canAccess(instance)
            field.isAccessible = true
            if (shouldEncode(field)) {
                jsonObject.addField(field.name, field.get(instance))
            }
            field.isAccessible = b
        }
        return jsonObject
    }

    private fun collectionEncoder(): JsonObject {
        val collection = type as Collection<*>
        val jsonObject = JsonObject()
        jsonObject.addField(collection.javaClass.simpleName, collectionContentsEncoder(collection))
        return jsonObject
    }

    private fun mapEncoder(): JsonObject {
        TODO("Not yet implemented")
    }

    private fun collectionContentsEncoder(collection: Collection<*>): JsonObject {
        val jsonObject = JsonObject()
        val jsonArray = JsonArray()
        for (item in collection) {
            if (item == null) {
                continue
            }
            if (item::class.java.isPrimitive || item::class.java == String::class.java) {
                jsonArray.addElement(item)
                continue
            }
            val fields: Array<out Field> = item::class.java.declaredFields
            val arrayField = JsonObject()
            for (field in fields) {
                val b = field.canAccess(item)
                field.isAccessible = true
                if (shouldEncode(field)) {
                    arrayField.addField(field.name, field.get(item))
                }
                field.isAccessible = b
            }
            jsonArray.addElement(arrayField)
        }
        jsonObject.addField("contents", jsonArray)
        return jsonObject
    }

    private fun shouldEncode(field: Field): Boolean {
        return field.type.isPrimitive || field.type == String::class.java
    }
}