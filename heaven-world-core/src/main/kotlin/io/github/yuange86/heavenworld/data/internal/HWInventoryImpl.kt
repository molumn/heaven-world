package io.github.yuange86.heavenworld.data.internal

import io.github.yuange86.heavenworld.data.HWInventory
import io.github.yuange86.heavenworld.getValue
import io.github.yuange86.heavenworld.inv.internal.HWInvAreaMakerImpl
import io.github.yuange86.heavenworld.inv.internal.HWInvSlotMakerImpl
import io.github.yuange86.heavenworld.lazyVal
import io.github.yuange86.heavenworld.setValue
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.Inventory

/**
 * @since 0.3.0
 */
open class HWInventoryImpl(
    height: Int,
    title: Component
) : HWInventory {

    protected val inv by lazy { Bukkit.createInventory(this, height * 9, title) }
    override fun getInventory(): Inventory = inv

    protected val slots = arrayListOf<HWInvSlotMakerImpl>()
    protected val areas = arrayListOf<HWInvAreaMakerImpl>()

    protected var openListener: ((InventoryOpenEvent) -> Unit)? by lazyVal()
    protected var closeListener: ((InventoryCloseEvent) -> Unit)? by lazyVal()
    protected var clickListener: ((Int, Int, InventoryClickEvent) -> Unit)? by lazyVal()
    protected var clickBottomListener: ((InventoryClickEvent) -> Unit)? by lazyVal()
    protected var clickOutsideListener: ((InventoryClickEvent) -> Unit)? by lazyVal()
    protected var creativeListener: ((InventoryCreativeEvent) -> Unit)? by lazyVal()
    protected var dragListener: ((InventoryDragEvent) -> Unit)? by lazyVal()
    protected var dropListener: ((PlayerDropItemEvent) -> Unit)? by lazyVal()
    protected var insertListener: ((InventoryPickupItemEvent) -> Unit)? by lazyVal()
    protected var pickupListener: ((EntityPickupItemEvent) -> Unit)? by lazyVal()

    override fun listenOpen(event: InventoryOpenEvent) {
        openListener?.invoke(event)
    }

    override fun listenClose(event: InventoryCloseEvent) {
        closeListener?.invoke(event)
    }

    override fun listenClick(event: InventoryClickEvent) {
        val x = event.rawSlot % 9
        val y = event.rawSlot / 9
        clickListener?.invoke(x, y, event)
        slots.find { it.x == x && it.y == y }?.clickListener?.invoke(event)
        areas.find { it.overlaps(x, y, x, y) }?.run {
            clickListener?.invoke(x - this.sx, y - this.sy, event)
        }
    }

    override fun listenClickBottom(event: InventoryClickEvent) {
        clickBottomListener?.invoke(event)
    }

    override fun listenClickOutside(event: InventoryClickEvent) {
        clickOutsideListener?.invoke(event)
    }

    override fun listenCreative(event: InventoryCreativeEvent) {
        creativeListener?.invoke(event)
    }

    override fun listenDrag(event: InventoryDragEvent) {
        dragListener?.invoke(event)
    }

    override fun listenDrop(event: PlayerDropItemEvent) {
        dropListener?.invoke(event)
    }

    override fun listenInsert(event: InventoryPickupItemEvent) {
        insertListener?.invoke(event)
    }

    override fun listenPickup(event: EntityPickupItemEvent) {
        pickupListener?.invoke(event)
    }

}