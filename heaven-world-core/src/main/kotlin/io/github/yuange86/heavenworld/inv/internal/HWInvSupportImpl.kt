package io.github.yuange86.heavenworld.inv.internal

import io.github.yuange86.heavenworld.data.HWInventory
import io.github.yuange86.heavenworld.inv.HWInvMaker
import io.github.yuange86.heavenworld.inv.HWInvSupport
import io.github.yuange86.heavenworld.inv.maker.HWInvShowerMaker
import io.github.yuange86.heavenworld.inv.maker.HWInvShowerMakerImpl
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryCreativeEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

/**
 * @since 0.3.0
 */
class HWInvSupportImpl(
    private val plugin: JavaPlugin
) : HWInvSupport, Listener {

    internal var listenerAppended = false
        private set

    private fun appendListener() {
        if(!listenerAppended) {
            plugin.server.pluginManager.registerEvents(this, plugin)
            listenerAppended = true
        }
    }

    override fun newInv(height: Int, title: Component): HWInvMaker {
        appendListener()
        return HWInvMakerImpl(height, title)
    }

    override fun newGUI(height: Int, title: Component): HWInvShowerMaker {
        appendListener()
        return HWInvShowerMakerImpl(height, title)
    }



    // HWInventory Listener

    @EventHandler
    fun onOpen(event: InventoryOpenEvent) {
        event.inventory.hw?.listenOpen(event)
        event.player.sendMessage(text("Did you see the inventory? ${event.inventory.hw == null}"))
    }
    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        event.inventory.hw?.listenClose(event)
    }
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val slot = event.rawSlot
        when {
            slot < 0 -> event.inventory.hw?.listenClickOutside(event)
            slot < event.inventory.size -> event.inventory.hw?.listenClick(event)
            else -> event.inventory.hw?.listenClickBottom(event)
        }
    }
    @EventHandler
    fun onCreative(event: InventoryCreativeEvent) {
        event.inventory.hw?.listenCreative(event)
    }
    @EventHandler
    fun onDrag(event: InventoryDragEvent) {
        event.inventory.hw?.listenDrag(event)
    }
    @EventHandler
    fun onDrop(event: PlayerDropItemEvent) {
        event.player.openInventory.topInventory.hw?.listenDrop(event)
    }
    @EventHandler
    fun onInsert(event: InventoryPickupItemEvent) {
        event.inventory.hw?.listenInsert(event)
    }
    @EventHandler
    fun onPickup(event: EntityPickupItemEvent) {
        val entity = event.entity
        if(entity is Player) {
            entity.openInventory.topInventory.hw?.listenPickup(event)
        }
    }


    private val Inventory.hw : HWInventory?
        get() = holder?.takeIf { it is HWInventory } as? HWInventory

}
