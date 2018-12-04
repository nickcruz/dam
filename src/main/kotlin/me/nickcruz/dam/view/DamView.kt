package me.nickcruz.dam.view

import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.stage.DirectoryChooser
import me.nickcruz.dam.viewmodel.ImageExplorerViewModel
import tornadofx.View
import tornadofx.button
import tornadofx.hbox
import tornadofx.label
import tornadofx.singleAssign
import tornadofx.vbox
import java.io.File

/**
 * Initial view for DAM. app.
 */
class DamView : View(VIEW_TITLE) {

    private var rootDirectoryLabel: Label by singleAssign()

    private val directoryChooser = DirectoryChooser().apply {
        title = CHOOSE_ROOT_DIRECTORY
        initialDirectory = File(INITIAL_DIRECTORY)
    }

    private val imageExplorerViewModel = ImageExplorerViewModel()

    init {
        imageExplorerViewModel.onRootDirectoryChanged { rootDirectoryLabel.text = it.absolutePath }
    }

    override val root = vbox {
        hbox {
            button(CHOOSE_ROOT_DIRECTORY).onAction = EventHandler {
                imageExplorerViewModel.rootDirectory = directoryChooser.showDialog(currentWindow)
            }
            rootDirectoryLabel = label()
        }
    }

    companion object {
        private const val VIEW_TITLE = "DAM."
        private const val CHOOSE_ROOT_DIRECTORY = "Choose a root directory"

        private val INITIAL_DIRECTORY = System.getProperty("user.home")
    }
}
