package io.github.yuange86.heavenworld.system

import io.github.yuange86.heavenworld.LibraryLoader

/**
 * @author yuange86
 * @since 0.1.0
 * implementation : yes
 */
interface HWPlayerSystem {

    companion object : HWPlayerSystem by LibraryLoader.loadImplement(HWPlayerSystem::class.java)

}
