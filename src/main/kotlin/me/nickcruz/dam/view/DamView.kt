package me.nickcruz.dam.view

import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.FlowPane
import javafx.stage.DirectoryChooser
import me.nickcruz.dam.viewmodel.ImageExplorerViewModel
import tornadofx.View
import tornadofx.button
import tornadofx.fitToParentSize
import tornadofx.flowpane
import tornadofx.hbox
import tornadofx.imageview
import tornadofx.label
import tornadofx.onChange
import tornadofx.scrollpane
import tornadofx.singleAssign
import tornadofx.vbox
import java.io.File

/**
 * Initial view for DAM. app.
 */
class DamView : View(VIEW_TITLE) {

    private var chooseRootDirectoryButton: Button by singleAssign()
    private var rootDirectoryLabel: Label by singleAssign()
    private var imageGalleryFlowPane: FlowPane by singleAssign()

    private val directoryChooser = DirectoryChooser().apply {
        title = CHOOSE_ROOT_DIRECTORY
        initialDirectory = File(INITIAL_DIRECTORY)
    }

    private val imageExplorerViewModel = ImageExplorerViewModel()

    override val root = vbox {
        hbox {
            chooseRootDirectoryButton = button(CHOOSE_ROOT_DIRECTORY)
            rootDirectoryLabel = label()
        }
        scrollpane(true, true) {
            fitToParentSize()
            imageGalleryFlowPane = flowpane {
                hgap = GALLERY_IMAGE_GAP
                vgap = hgap
            }
        }
    }

    init {
        chooseRootDirectoryButton.onAction = EventHandler {
            directoryChooser.showDialog(currentWindow)?.let { directory ->
                imageExplorerViewModel.rootDirectory = directory
            }
        }
        imageExplorerViewModel.onRootDirectoryChanged { rootDirectoryLabel.text = it.absolutePath }
        imageExplorerViewModel.images.onChange {
            imageGalleryFlowPane.children.setAll(it.list.map { file ->
                imageview(file.toURI().toString()) {
                    isPreserveRatio = true
                    fitHeight = 300.0
                }
            })
        }
    }

    companion object {
        private const val VIEW_TITLE = "DAM."
        private const val CHOOSE_ROOT_DIRECTORY = "Choose a root directory"
        private const val GALLERY_IMAGE_GAP = 4.0

        private val INITIAL_DIRECTORY = System.getProperty("user.home")
    }
}
