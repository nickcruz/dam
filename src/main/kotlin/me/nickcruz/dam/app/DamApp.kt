package me.nickcruz.dam.app

import javafx.stage.Stage
import me.nickcruz.dam.view.DamView
import tornadofx.App

class DamApp : App(DamView::class) {
    override fun start(stage: Stage) {
        stage.minHeight = 200.0
        stage.minWidth = 400.0
        super.start(stage)
    }
}