package com.github.wildtooth.tooling

import com.github.wildtooth.tooling.dummy.DummyObject
import java.io.File

fun main() {
    val saveEncoder = SaveEncoder(listOf("LOL", "GOOFY", "Hmm"), File("saves"))
    saveEncoder.encode("dummy.json")
}