package me.nickcruz.dam.viewmodel

import tornadofx.getProperty
import tornadofx.onChange
import tornadofx.property
import java.io.File

/**
 * Exposes all images in a given root directory, recursively.
 */
class ImageExplorerViewModel {

    var rootDirectory: File by property()

    init {
        onRootDirectoryChanged { updateImagePaths() }
    }

    fun onRootDirectoryChanged(op: (File) -> Unit) {
        getProperty(ImageExplorerViewModel::rootDirectory).onChange { it?.let(op) }
    }

    private fun updateImagePaths() {
        println(rootDirectory.absolutePath)
    }
}
