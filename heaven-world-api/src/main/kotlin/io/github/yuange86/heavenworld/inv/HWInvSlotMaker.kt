package io.github.yuange86.heavenworld.inv

import io.github.yuange86.heavenworld.annotation.HWInvDSL
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @since 0.3.0
 */
@HWInvDSL
interface HWInvSlotMaker {
    val x: Int
    val y: Int
    var item: ItemStack?
    fun onClick(listener: (event: InventoryClickEvent) -> Unit)
}