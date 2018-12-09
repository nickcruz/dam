package me.nickcruz.dam.control

import javafx.collections.ObservableList
import javafx.scene.layout.FlowPane
import tornadofx.fitToParentSize
import tornadofx.imageview
import tornadofx.onChange
import java.io.File

/**
 * Observes a list of [File]s and shows them in a gallery format.
 */
class ImageGallery(images: ObservableList<File>) : FlowPane() {

    init {
        fitToParentSize()
        hgap = GAP
        vgap = GAP

        images.onChange {
            children.setAll(it.list.map { file ->
                imageview(file.toURI().toString()) {
                    isPreserveRatio = true
                    fitHeight = 400.0
                }
            })
        }
    }

    companion object {
        private const val GAP = 4.0
    }
}
