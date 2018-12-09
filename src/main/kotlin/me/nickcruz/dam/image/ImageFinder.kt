package me.nickcruz.dam.image

import java.io.File
import java.util.LinkedList
import java.util.Queue

/**
 * Iteratively finds all images given a root directory.
 */
class ImageFinder {

    fun findImages(rootDirectory: File): List<File> {
        val queue: Queue<File> = LinkedList()
        val imagesFound = ArrayList<File>()
        queue.add(rootDirectory)
        while (queue.isNotEmpty()) {
            queue
                .remove()
                .listFiles()
                .forEach {
                    when {
                        it.isDirectory -> queue.add(it)
                        it.isImage() -> imagesFound.add(it)
                    }
                }
        }
        return imagesFound
    }

    private fun File.isImage(): Boolean {
        return absolutePath.endsWith(EXTENSION_JPG, true)
    }

    companion object {
        private const val EXTENSION_JPG = ".jpg"
    }
}