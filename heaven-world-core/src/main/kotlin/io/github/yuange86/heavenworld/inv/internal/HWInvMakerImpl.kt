package io.github.yuange86.heavenworld.inv.internal

import io.github.yuange86.heavenworld.data.internal.HWInventoryImpl
import io.github.yuange86.heavenworld.inv.HWInvAreaMaker
import io.github.yuange86.heavenworld.inv.HWInvMaker
import io.github.yuange86.heavenworld.inv.HWInvSlotMaker
import net.kyori.adventure.text.Component
import org.bukkit.event.inventory.*
import org.bukkit.inventory.ItemStack

/**
 * @since 0.3.0
 */
class HWInvMakerImpl(
    height: Int,
    title: Component
) : HWInvMaker, HWInventoryImpl(height, title) {

    override fun background(item: ItemStack?) {
        require(slots.isEmpty()) { "There are one or more slots has set, please call this function primarily" }
        require(areas.isEmpty()) { "There are one or more areas has set, please call this function primarily" }
        require(inv.contents.all { it == null }) { "There are one or more contents in Bukkit Inventory" }
        for(index in 0 until inv.size) {
            inv.setItem(index, item)
        }
    }

    private fun checkPosition(x: Int, y: Int) {
        val height = inv.size / 9
        require(x in 0 until 9) { "x is out of range : 0 <= $x < 9" }
        require(y in 0 until height) { "y is out of range : 0 <= $y < $height" }
        require(slots.find { it.x == x && it.y == y } == null) { "cannot overwrite the slot[x: $x, y: $x]" }

    }

    private fun checkArea(sx: Int, sy: Int, dx: Int, dy: Int) {
        val ex = sx + dx
        val ey = sy + dy
        val height = inv.size / 9
        require(sx in 0 until 9 && ex <= 9) { "sx, ex is out of range : 0 <= ($sx, $ex) < 9" }
        require(sy in 0 until height && ey <= height) { "sy, ey is out of range : 0 <= ($sy, $ey) < $height" }
        require(slots.find { it.x in sx until ex && it.y in sy until ey } == null) { "cannot overwrite the slot(s) in area[$sx ~ $ex, $sy ~ $ey]" }
        require(areas.find { it.overlaps(sx, sy, ex, ey) } == null) { "cannot overwrite the area(s) in area[$sx ~ $ex, $sy ~ $ey]" }
    }

    override fun slot(x: Int, y: Int, init: HWInvSlotMaker.() -> Unit) {
        checkPosition(x, y)
        HWInvSlotMakerImpl(this, x, y).apply(init).also { slots += it }
    }

    override fun area(sx: Int, sy: Int, dx: Int, dy: Int, init: HWInvAreaMaker.() -> Unit) {
        checkArea(sx, sy, dx, dy)
        HWInvAreaMakerImpl(this, sx, sy, dx, dy).apply(init).also { areas += it }
    }

    override fun area(xp: Pair<Int, Int>, yp: Pair<Int, Int>, init: HWInvAreaMaker.() -> Unit) {
        area(xp.first, yp.first, xp.second - xp.first, yp.second - yp.first, init)
    }

    override fun onOpen(listener: (event: InventoryOpenEvent) -> Unit) {
        openListener = listener
    }

    override fun onClose(listener: (event: InventoryCloseEvent) -> Unit) {
        closeListener = listener
    }

    override fun onClick(listener: (x: Int, y: Int, event: InventoryClickEvent) -> Unit) {
        clickListener = listener
    }

    override fun onClickBottom(listener: (event: InventoryClickEvent) -> Unit) {
        clickBottomListener = listener
    }

    override fun onClickOutside(listener: (event: InventoryClickEvent) -> Unit) {
        clickOutsideListener = listener
    }


    override fun item(x: Int, y: Int): ItemStack? = inv.getItem(x + y * 9)

    override fun setItem(x: Int, y: Int, itemStack: ItemStack?) = inv.setItem(x + y * 9, itemStack)

}