package io.github.yuange86.heavenworld.traits

import java.io.File

/**
 * this is only to serve the trait interface
 * @author yuange86
 * @since
 */
interface AbleToPatch {
    fun patch(yamlFile: File)
}