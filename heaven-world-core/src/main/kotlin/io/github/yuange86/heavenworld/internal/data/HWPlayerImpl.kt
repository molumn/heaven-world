package io.github.yuange86.heavenworld.internal.data

import io.github.yuange86.heavenworld.data.HWPlayer
import io.github.yuange86.heavenworld.objectives.RelativeDirectories

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player

import java.io.File

/**
 * @author yuange86
 * @since 0.1.0
 * todo("not tested")
 */
@Suppress("unused")
class HWPlayerImpl(
    override val player: Player,
    patch: Boolean = false,
    private val defaultRelativeDirectoryPath: String = RelativeDirectories.PLAYER_DIR
) : HWPlayer {

    private var status: HashMap<String, Any> = HashMap()

    init {
        if(patch) {
            patch(File(defaultRelativeDirectoryPath, "${player.uniqueId}.yml"))
        }
    }

    override fun patch(yamlFile: File) {
        yamlFile.let { YamlConfiguration.loadConfiguration(yamlFile) }
            .getConfigurationSection("user.status")?.run {
                getKeys(true).forEach {
                    get(it)?.let { value ->
                        status[it] = value
                    }
                }
            } ?: throw NoSuchElementException("no such player's data or there is no stored data for the player")
    }

    override fun storage(yamlFile: File) {
        yamlFile.let { YamlConfiguration.loadConfiguration(yamlFile) }
            .let {
                it.createSection("player").run {
                    set("name", player.displayName())
                    set("uuid", player.uniqueId)
                    createSection("status", status)
                }
                it.save(yamlFile)
            }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T: Any> getStatus(key: String) : T = status[key] as T

    override fun <T: Any> setStatus(key: String, value: T) {
        status[key] = value
    }


}