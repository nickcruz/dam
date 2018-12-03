package me.nickcruz.dam.app

import javafx.stage.Stage
import tornadofx.App
import me.nickcruz.dam.ui.MainView

class DamApp : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.minHeight = 200.0
        stage.minWidth = 400.0
        super.start(stage)
    }
}