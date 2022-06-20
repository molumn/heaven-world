package io.github.yuange86.heavenworld.inv.internal

import io.github.yuange86.heavenworld.getValue
import io.github.yuange86.heavenworld.inv.HWInvMaker
import io.github.yuange86.heavenworld.inv.HWInvSlotMaker
import io.github.yuange86.heavenworld.lazyVal
import io.github.yuange86.heavenworld.setValue
import io.github.yuange86.heavenworld.weak
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @since 0.3.0
 */
class HWInvSlotMakerImpl(
    maker: HWInvMaker,
    override val x: Int,
    override val y: Int
) : HWInvSlotMaker {
    private val maker by weak(maker)

    internal var clickListener: ((InventoryClickEvent) -> Unit)? by lazyVal()
        private set

    override var item: ItemStack?
        get() = maker.item(x, y)
        set(value) {
            maker.setItem(x, y, value)
        }

    override fun onClick(listener: (event: InventoryClickEvent) -> Unit) {
        clickListener = listener
    }

}