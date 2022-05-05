package io.github.yuange86.heavenworld.manager

import io.github.yuange86.heavenworld.LibraryLoader
import io.github.yuange86.heavenworld.data.HWPlayer

import org.bukkit.entity.Player

/**
 * @author yuange86
 * @since 0.2.0
 * implementation: yes
 * tested: 0.2.0
 */
interface HWPlayerManager {

    companion object {
        fun create() : HWPlayerManager = LibraryLoader.loadImplement(HWPlayerManager::class.java)
    }

    /**
     * @return the bound [HWPlayer] class of player in parameter
     */
    fun addPlayer(player: Player, patch: Boolean = false) : HWPlayer
    /**
     * @return the bound [HWPlayer] class of player in parameter
     */
    fun removePlayer(player: Player, storage: Boolean = true) : HWPlayer?

    fun getHWPlayer(player: Player) : HWPlayer

    fun forEachPlayer(callbackFn: HWPlayer.() -> Unit)

    fun <Left> fold(initialValue: Left, foldFn: (Left, HWPlayer) -> Left) : Left


    fun patchAll()

}