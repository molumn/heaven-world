package io.github.yuange86.heavenworld.inv

import io.github.yuange86.heavenworld.annotation.HWInvDSL
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @since 0.3.0
 */
@HWInvDSL
interface HWInvAreaMaker {

    fun onClick(listener: (x: Int, y: Int, event: InventoryClickEvent) -> Unit)

    fun item(x: Int, y: Int): ItemStack?

    fun setItem(x: Int, y: Int, itemStack: ItemStack?)

    fun background(item: ItemStack?)

}
