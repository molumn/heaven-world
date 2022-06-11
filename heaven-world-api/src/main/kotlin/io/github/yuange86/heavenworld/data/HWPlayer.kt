package io.github.yuange86.heavenworld.data

import io.github.yuange86.heavenworld.traits.AbleToPatch
import io.github.yuange86.heavenworld.traits.AbleToStorage

import org.bukkit.entity.Player

import kotlin.reflect.KProperty

/**
 * implementation : yes
 * tested: 0.2.0
 * @author yuange86
 * @since 0.1.0
 */
interface HWPlayer : AbleToPatch, AbleToStorage {
    val player: Player
    val defaultRelativeDirectoryPath: String

    /**
     * ### yaml key 형식을 사용하므로 admin 과 admin.b 같은 겹친 표현은 사용 불가
     */
    fun <T: Any> getStatus(key: String) : T

    /**
     * ### yaml key 형식을 사용하므로 admin 과 admin.b 같은 겹친 표현은 사용 불가
     */
    fun <T: Any> setStatus(key: String, value: T)
}

operator fun <T: Any> HWPlayer.getValue(thisRef: String, property: KProperty<*>) : T {
    return getStatus(property.name)
}
