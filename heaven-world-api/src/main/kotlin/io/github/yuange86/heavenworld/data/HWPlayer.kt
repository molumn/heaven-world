package io.github.yuange86.heavenworld.data

import io.github.yuange86.heavenworld.traits.AbleToPatch
import io.github.yuange86.heavenworld.traits.AbleToStorage

import org.bukkit.entity.Player
import kotlin.reflect.KProperty

/**
 * @author yuange86
 * @since 0.1.0
 * implementation : yes
 */
interface HWPlayer : AbleToPatch, AbleToStorage {
    val player: Player

    fun <T: Any> getStatus(key: String) : T
    fun <T: Any> setStatus(key: String, value: T)
}

operator fun <T: Any> HWPlayer.getValue(thisRef: String, property: KProperty<*>) : T {
    return getStatus(property.name)
}
