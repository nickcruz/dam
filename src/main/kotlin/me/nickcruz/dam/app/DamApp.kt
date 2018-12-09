package me.nickcruz.dam.app

import javafx.stage.Stage
import me.nickcruz.dam.view.DamView
import tornadofx.App

class DamApp : App(DamView::class) {
    override fun start(stage: Stage) {
        stage.isMaximized = true
        super.start(stage)
    }
}