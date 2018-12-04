package me.nickcruz.dam.view

import javafx.scene.control.TextField
import me.nickcruz.dam.viewmodel.ImageExplorerViewModel
import tornadofx.View
import tornadofx.hbox
import tornadofx.label
import tornadofx.onChange
import tornadofx.singleAssign
import tornadofx.textfield
import tornadofx.vbox

/**
 * Initial view for DAM. app.
 */
class DamView : View(VIEW_TITLE) {

    private var rootDirectoryField: TextField by singleAssign()
    private val imageExplorerViewModel = ImageExplorerViewModel()

    override val root = vbox {
        hbox {
            label("Root Directory")
            rootDirectoryField = textfield {
                textProperty().onChange { it?.run { imageExplorerViewModel.setRootDirectory(it) } }
            }
        }
    }

    companion object {
        private const val VIEW_TITLE = "DAM."
    }
}
