package com.github.wildtooth.tooling

import java.io.File

interface SaveHandler {
    fun getSaveFolder(): File
}