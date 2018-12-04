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
        // TODO(nick): Make this recursively iterate through the file tree.
        // TODO(nick): Set up a coroutine channel that all images found.
        rootDirectory
            .listFiles { file -> file.isDirectory || file.absolutePath.endsWith(".jpg", true) }
            .forEach { println(it) } // TODO(nick): Instead of printing, show the filenames in a ListView.
    }
}
