package com.github.wildtooth.tooling.json

class JsonArray {
    private val elements: MutableList<Any> = mutableListOf()

    fun addElement(element: Any) {
        elements.add(element)
    }

    fun removeElement(element: Any) {
        elements.remove(element)
    }

    fun getElement(index: Int): Any {
        return elements[index]
    }

    fun getElements(): List<Any> {
        return elements
    }

    fun clear() {
        elements.clear()
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return elements.isNotEmpty()
    }

    fun size(): Int {
        return elements.size
    }

    fun encode(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (element in elements) {
            when (element) {
                is String -> sb.append("\"$element\"")
                is Number -> sb.append(element)
                is Boolean -> sb.append(element)
                is JsonObject -> sb.append(element.encode())
                is JsonArray -> sb.append(element.encode())
                else -> {
                    throw IllegalArgumentException("Type not supported")
                }
            }
            sb.append(",")
        }
        if (elements.isNotEmpty()) {
            sb.deleteCharAt(sb.length - 1)
        }
        sb.append("]")
        return sb.toString()
    }

    fun decode(json: String): JsonArray {
        val jsonArray = JsonArray()
        val elements = json.substring(1, json.length - 1).split(",")
        for (element in elements) {
            jsonArray.addElement(element)
        }
        return jsonArray
    }
}