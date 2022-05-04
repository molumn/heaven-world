package io.github.yuange86.heavenworld.traits

import java.io.File

/**
 * @author yuange86
 * @since
 * this is only for serve the trait interface
 */
interface AbleToPatch {
    fun patch(yamlFile: File)
}