package me.nickcruz.dam.view

import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.DirectoryChooser
import me.nickcruz.dam.control.imagegallery
import me.nickcruz.dam.viewmodel.ImageExplorerViewModel
import tornadofx.View
import tornadofx.button
import tornadofx.fitToParentSize
import tornadofx.hbox
import tornadofx.label
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
            imagegallery(imageExplorerViewModel.images)
        }
    }

    init {
        chooseRootDirectoryButton.onAction = EventHandler {
            directoryChooser.showDialog(currentWindow)?.let { directory ->
                imageExplorerViewModel.rootDirectory = directory
            }
        }
        imageExplorerViewModel.onRootDirectoryChanged { rootDirectoryLabel.text = it.absolutePath }
    }

    companion object {
        private const val VIEW_TITLE = "DAM."
        private const val CHOOSE_ROOT_DIRECTORY = "Choose a root directory"

        private val INITIAL_DIRECTORY = System.getProperty("user.home")
    }
}
