package io.github.yuange86.heavenworld.objectives

import io.github.yuange86.heavenworld.plugin.HWPlugin
import java.io.File

/**
 * storage directories
 * tested: 0.2.0
 * @author yuange86
 * @since 0.1.0
 */
object RelativeDirectories {
    val DATA_FOLDER: String = HWPlugin.instance.dataFolder.absolutePath

    val PLAYER_DIR: String = "$DATA_FOLDER/players"

    fun prepareAll() {
        File(PLAYER_DIR).mkdirs()
    }
}