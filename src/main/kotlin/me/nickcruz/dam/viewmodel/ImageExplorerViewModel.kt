package me.nickcruz.dam.viewmodel

import tornadofx.getProperty
import tornadofx.onChange
import tornadofx.property

/**
 * Exposes all images in a given root directory, recursively.
 */
class ImageExplorerViewModel {

    var rootDirectory: String by property()

    init {
        getProperty(ImageExplorerViewModel::rootDirectory).onChange { onRootDirectoryChanged() }
    }

    private fun onRootDirectoryChanged() {
        println(rootDirectory)
    }
}
