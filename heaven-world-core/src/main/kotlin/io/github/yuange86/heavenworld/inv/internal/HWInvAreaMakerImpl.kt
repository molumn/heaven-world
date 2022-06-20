package io.github.yuange86.heavenworld.inv.internal

import io.github.yuange86.heavenworld.getValue
import io.github.yuange86.heavenworld.inv.HWInvAreaMaker
import io.github.yuange86.heavenworld.inv.HWInvMaker
import io.github.yuange86.heavenworld.lazyVal
import io.github.yuange86.heavenworld.setValue
import io.github.yuange86.heavenworld.weak
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @since 0.3.0
 */
class HWInvAreaMakerImpl(
    maker: HWInvMaker,
    val sx: Int,
    val sy: Int,
    val dx: Int,
    val dy: Int
) : HWInvAreaMaker {

    private val maker by weak(maker)

    internal var clickListener: ((Int, Int, InventoryClickEvent) -> Unit)? by lazyVal()
        private set

    fun overlaps(sx: Int, sy: Int, ex: Int, ey: Int) : Boolean {
        return !(this.sx >= ex || this.sy >= ey || sx >= this.sx + dx || sy >= this.sy + dy)
    }

    override fun background(item: ItemStack?) {
        for(x in sx until sx + dx) {
            for(y in sy until  sy + dy) {
                maker.setItem(x, y, item)
            }
        }
    }

    override fun item(x: Int, y: Int): ItemStack? {
        require(x < dx && y < dy) { "relative coordinate x, y is out of range : [0 <= $x < $dx, 0 <= $y < $dy]" }
        return maker.item(sx + x, sy + y)
    }

    override fun setItem(x: Int, y: Int, itemStack: ItemStack?) {
        require(x < dx && y < dy) { "relative coordinate x, y is out of range : [0 <= $x < $dx, 0 <= $y < $dy]" }
        maker.setItem(sx + x, sy + y, itemStack)
    }

    override fun onClick(listener: (x: Int, y: Int, event: InventoryClickEvent) -> Unit) {
        clickListener = listener
    }

}