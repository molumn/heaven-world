package io.github.yuange86.heavenworld.objectives

import java.io.File

/**
 * @author yuange86
 * @since 0.1.0
 * storage directories
 * tested: 0.2.0
 */
object RelativeDirectories {
    const val PLAYER_DIR: String = "plugins/heaven-world/players"

    fun prepareAll() {
        File(PLAYER_DIR).mkdirs()
    }
}