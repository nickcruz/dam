package me.nickcruz.dam.viewmodel

import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import me.nickcruz.dam.image.ImageFinder
import tornadofx.FX.Companion.log
import tornadofx.getProperty
import tornadofx.onChange
import tornadofx.property
import java.io.File

/**
 * Exposes all images in a given root directory, recursively.
 */
class ImageExplorerViewModel {

    var rootDirectory: File by property()
    val images: ObservableList<File> = observableArrayList<File>()
    private val imageFinder = ImageFinder()

    init {
        onRootDirectoryChanged { updateImagePaths() }
    }

    fun onRootDirectoryChanged(op: (File) -> Unit) {
        getProperty(ImageExplorerViewModel::rootDirectory).onChange { it?.let(op) }
    }

    private fun updateImagePaths() {
        images.setAll(imageFinder.findImages(rootDirectory))
        log.info("Number of images found: ${images.size}")
    }
}
