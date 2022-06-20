package io.github.yuange86.heavenworld.plugin

import io.github.yuange86.heavenworld.objectives.RelativeDirectories
import org.bukkit.plugin.java.JavaPlugin

abstract class HWPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: HWPlugin
        private set
    }

    override fun onEnable() {
        instance = this
        RelativeDirectories.prepareAll()
    }

}
