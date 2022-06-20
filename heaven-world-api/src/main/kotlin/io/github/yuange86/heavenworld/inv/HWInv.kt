package io.github.yuange86.heavenworld.inv

import io.github.yuange86.heavenworld.LibraryLoader
import io.github.yuange86.heavenworld.annotation.HWInvDSL
import io.github.yuange86.heavenworld.data.HWInventory
import io.github.yuange86.heavenworld.inv.maker.HWInvShowerMaker
import io.github.yuange86.heavenworld.plugin.HWPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player

/**
 * @since 0.3.0
 */
object HWInv {
    internal val support: HWInvSupport by lazy { LibraryLoader.loadImplement(HWInvSupport::class.java, HWPlugin.instance) }

    @HWInvDSL
    fun inv(height: Int, title: String, init: HWInvMaker.() -> Unit): HWInvMaker = inv(height, text(title), init)
    @HWInvDSL
    fun inv(height: Int, title: Component, init: HWInvMaker.() -> Unit) : HWInvMaker {
        return support.newInv(height, title).apply(init)
    }

    @HWInvDSL
    fun shower(height: Int, title: Component, init: HWInvShowerMaker.() -> Unit) : HWInvShowerMaker {
        return support.newGUI(height, title).apply(init)
    }

}

interface HWInvSupport {

    fun newInv(height: Int, title: Component) : HWInvMaker
    fun newGUI(height: Int, title: Component) : HWInvShowerMaker

}

fun Player.openHWInv(hwInventory: HWInventory) {
    openInventory(hwInventory.inventory)
}

fun Player.openHWInv(hwInvMaker: HWInvMaker) {
    require(hwInvMaker is HWInventory)
    openInventory(hwInvMaker.inventory)
}

