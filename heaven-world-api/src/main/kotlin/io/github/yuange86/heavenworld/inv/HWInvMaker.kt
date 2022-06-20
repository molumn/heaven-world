package io.github.yuange86.heavenworld.inv

import io.github.yuange86.heavenworld.annotation.HWInvDSL
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

/**
 * @since 0.3.0
 */
@HWInvDSL
interface HWInvMaker : HWInvAreaMaker {

    fun slot(x: Int, y: Int, init: HWInvSlotMaker.() -> Unit)
    fun area(sx: Int, sy: Int, dx: Int, dy: Int, init: HWInvAreaMaker.() -> Unit)
    fun area(xp: Pair<Int, Int>, yp: Pair<Int, Int>, init: HWInvAreaMaker.() -> Unit)

    fun onOpen(listener: (event: InventoryOpenEvent) -> Unit)
    fun onClose(listener: (event: InventoryCloseEvent) -> Unit)
    fun onClickBottom(listener: (event: InventoryClickEvent) -> Unit)
    fun onClickOutside(listener: (event: InventoryClickEvent) -> Unit)

}
