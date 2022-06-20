package io.github.yuange86.heavenworld.data

import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryCreativeEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.InventoryHolder

/**
 * @since 0.3.0
 */
interface HWInventory: InventoryHolder {

    fun listenOpen(event: InventoryOpenEvent)

    fun listenClose(event: InventoryCloseEvent)

    fun listenClick(event: InventoryClickEvent)
    fun listenClickBottom(event: InventoryClickEvent)
    fun listenClickOutside(event: InventoryClickEvent)

    fun listenCreative(event: InventoryCreativeEvent)

    fun listenDrag(event: InventoryDragEvent)

    fun listenDrop(event: PlayerDropItemEvent)

    fun listenInsert(event: InventoryPickupItemEvent)

    fun listenPickup(event: EntityPickupItemEvent)

}
