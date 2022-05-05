package io.github.yuange86.heavenworld.plugin

import io.github.yuange86.heavenworld.manager.HWPlayerManager

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

import java.io.File

@Suppress("unused")
class HeavenWorldPlugin: HWPlugin(), Listener {

    private val hwPlayerManager: HWPlayerManager = HWPlayerManager.create()

    override fun onEnable() {
        super.onEnable()

        server.pluginManager.registerEvents(this, this)

        hwPlayerManager.patchAll()
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        hwPlayerManager.addPlayer(event.player).run {
            setStatus("connection", "online")
            storage(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
            patch(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        hwPlayerManager.removePlayer(event.player)?.run {
            setStatus("connection", "offline")
            storage(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
        }
    }

}