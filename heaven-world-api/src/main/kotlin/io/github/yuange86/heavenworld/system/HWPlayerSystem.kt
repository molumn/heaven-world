package io.github.yuange86.heavenworld.system

import io.github.yuange86.heavenworld.LibraryLoader

/**
 * implementation : yes
 * @author yuange86
 * @since
 */
@Deprecated("Not Defined", level = DeprecationLevel.WARNING)
interface HWPlayerSystem {

    companion object : HWPlayerSystem by LibraryLoader.loadImplement(HWPlayerSystem::class.java)

}
