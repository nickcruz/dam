package me.nickcruz.dam.control

import javafx.collections.ObservableList
import javafx.event.EventTarget
import tornadofx.opcr
import java.io.File

/** Contains extension functions for Control package. */

fun EventTarget.imagegallery(images: ObservableList<File>, op: ImageGallery.() -> Unit = {}) =
    opcr(this, ImageGallery(images), op)
