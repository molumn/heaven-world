package io.github.yuange86.heavenworld.plugin

import io.github.yuange86.heavenworld.inv.HWInv
import io.github.yuange86.heavenworld.inv.openHWInv
import io.github.yuange86.heavenworld.manager.HWPlayerManager
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class HeavenWorldPlugin: HWPlugin(), Listener {

    private val hwPlayerManager: HWPlayerManager = HWPlayerManager.create()

    override fun onEnable() {
        super.onEnable()

        server.pluginManager.registerEvents(this, this)

//        hwPlayerManager.patchAll()  // todo: multi-thread?
    }


    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        HWInv.inv(5, "TEST") {
            background(ItemStack(Material.GRAY_STAINED_GLASS_PANE))
            area(0 to 3, 1 to 4) {
                background(null)
                setItem(1, 1, ItemStack(Material.WOODEN_AXE))
            }
            area(4 to 5, 0 to 5) {
                background(ItemStack(Material.BLACK_STAINED_GLASS_PANE))
            }
            area(6 to 9, 1 to 4) {
                background(null)
                setItem(
                    1,
                    1,
                    ItemStack(Material.NETHERITE_AXE).apply {
                        editMeta {
                            it.displayName(text("STRONG", TextColor.color(255, 255, 60)))
                        }
                    }
                )
            }
            onOpen { _ ->
                logger.warning("inventory opened")
            }
            onClick { _, _, event ->
                event.isCancelled = true
            }
        }.let {
            event.player.openHWInv(it)
        }
    }


    /**
     * ```kotlin
     *  @EventHandler
     *  fun onPlayerJoin(event: PlayerJoinEvent) {
     *      hwPlayerManager.addPlayer(event.player).run {
     *          setStatus("connection", "online")
     *          storage(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
     *          patch(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
     *      }
     *  }
     *
     *  @EventHandler
     *  fun onPlayerQuit(event: PlayerQuitEvent) {
     *      hwPlayerManager.removePlayer(event.player)?.run {
     *          setStatus("connection", "offline")
     *          storage(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
     *      }
     *  }
     *  ```
    */
    fun exampleHWPlayerManager() {}

}