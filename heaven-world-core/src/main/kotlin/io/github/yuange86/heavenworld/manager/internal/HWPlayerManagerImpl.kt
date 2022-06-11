package io.github.yuange86.heavenworld.manager.internal

import io.github.yuange86.heavenworld.data.HWPlayer
import io.github.yuange86.heavenworld.data.internal.HWPlayerImpl
import io.github.yuange86.heavenworld.manager.HWPlayerManager
import io.github.yuange86.heavenworld.objectives.RelativeDirectories

import org.bukkit.entity.Player

import java.io.File
import java.util.*

import kotlin.NoSuchElementException
import kotlin.collections.HashMap


/**
 * tested: 0.2.0
 * @author yuange86
 * @since 0.2.0
 */
@Suppress("unused")
class HWPlayerManagerImpl : HWPlayerManager {

    private val hwPlayers: HashMap<UUID, HWPlayer> = HashMap()

    override fun addPlayer(player: Player, patch: Boolean): HWPlayer {
        val hwPlayerImpl = HWPlayerImpl(player, patch)
        hwPlayers[player.uniqueId] = hwPlayerImpl
        return hwPlayerImpl
    }

    override fun removePlayer(player: Player, storage: Boolean): HWPlayer? =
        hwPlayers.remove(player.uniqueId)

    override fun getHWPlayer(player: Player): HWPlayer {
        return hwPlayers[player.uniqueId]
            ?: throw NoSuchElementException("Such Player[name: ${player.name}] is not online or not joined this server yet")
    }

    override fun forEachPlayer(callbackFn: HWPlayer.() -> Unit) {
        hwPlayers.values.forEach {
            it.callbackFn()
        }
    }

    override fun <Left> fold(initialValue: Left, foldFn: (Left, HWPlayer) -> Left) : Left =
        hwPlayers.values.fold(initialValue, foldFn)

    override fun patchAll() =
        hwPlayers.values.forEach { it.patch(File(RelativeDirectories.PLAYER_DIR, "${it.player.uniqueId}.yml")) }

}