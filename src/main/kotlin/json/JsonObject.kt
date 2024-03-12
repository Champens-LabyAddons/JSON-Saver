package com.github.wildtooth.tooling.json

class JsonObject {
    private val fields: MutableMap<String, Any> = mutableMapOf()

    fun addField(name: String, value: Any) {
        fields[name] = value
    }

    fun removeField(name: String) {
        fields.remove(name)
    }

    fun getField(name: String): Any? {
        return fields[name]
    }

    fun getFields(): Map<String, Any> {
        return fields
    }

    fun clear() {
        fields.clear()
    }

    fun isEmpty(): Boolean {
        return fields.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return fields.isNotEmpty()
    }

    fun size(): Int {
        return fields.size
    }

    fun encode(): String {
        val sb = StringBuilder()
        sb.append("{")
        for ((key, value) in fields) {
            sb.append("\"$key\":")
            when (value) {
                is String -> sb.append("\"$value\"")
                is Number -> sb.append(value)
                is Boolean -> sb.append(value)
                is JsonObject -> sb.append(value.encode())
                is JsonArray -> sb.append(value.encode())
                else -> {
                    throw IllegalArgumentException("Type not supported")
                }
            }
            sb.append(",")
        }
        if (fields.isNotEmpty()) {
            sb.deleteCharAt(sb.length - 1)
        }
        sb.append("}")
        return sb.toString()
    }

    fun decode(json: String): JsonObject {
        val jsonObject = JsonObject()
        val fields = json.substring(1, json.length - 1).split(",")
        for (field in fields) {
            val pair = field.split(":")
            jsonObject.addField(pair[0], pair[1])
        }
        return jsonObject
    }
}