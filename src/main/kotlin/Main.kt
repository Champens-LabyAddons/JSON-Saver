package com.github.wildtooth.tooling

import com.github.wildtooth.tooling.dummy.DummyObject
import java.io.File

fun main() {
    val saveEncoder = SaveEncoder(DummyObject::class.java, File("saves"))
    saveEncoder.encode()
}