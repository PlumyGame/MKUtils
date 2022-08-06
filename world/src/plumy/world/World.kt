package plumy.world

import arc.math.geom.Point2
import arc.math.geom.Position
import mindustry.Vars
import mindustry.gen.Building
import mindustry.world.Tile

typealias TileXY = Int
typealias TileXYs = Short
typealias TileXYf = Float
typealias TileXYd = Double
typealias WorldXY = Float
typealias PackedPos = Int
typealias Pos = Point2

/**
 * Try to get a building on this packed coordinate.
 */
inline fun <reified T : Building> PackedPos.castBuild(): T? =
    Vars.world.build(this) as? T
/**
 * Unpack a packed coordinate.
 * @see [Point2.unpack]
 * @see [Building.pos]
 */
fun PackedPos.unpack(): Pos =
    Point2.unpack(this)

fun tileAt(x: TileXY, y: TileXY): Tile? =
    Vars.world.tile(x, y)

fun tileAt(x: TileXYf, y: TileXYf): Tile? =
    Vars.world.tile(x.toInt(), y.toInt())

fun tileAt(x: TileXYd, y: TileXYd): Tile? =
    Vars.world.tile(x.toInt(), y.toInt())

fun buildAt(x: TileXY, y: TileXY): Building? =
    Vars.world.build(x, y)

fun buildAt(x: TileXYf, y: TileXYf): Building? =
    Vars.world.build(x.toInt(), y.toInt())

fun buildAt(x: TileXYd, y: TileXYd): Building? =
    Vars.world.build(x.toInt(), y.toInt())
/**
 * Try to get a building on this packed coordinate.
 * @see [Point2.unpack]
 * @see [Building.pos]
 */
val PackedPos.build: Building?
    get() = Vars.world.build(this)
/**
 * Try to get a building on this coordinate.
 */
val Pos.build: Building?
    get() = Vars.world.build(x, y)
/**
 * Tile coordinate to world coordinate
 */
val TileXYs.worldXY: WorldXY
    get() = this.toFloat() * Vars.tilesize
/**
 * Tile coordinate to world coordinate
 */
val TileXY.worldXY: WorldXY
    get() = this.toFloat() * Vars.tilesize
/**
 * Tile coordinate to world coordinate
 */
val TileXYf.worldXY: WorldXY
    get() = this * Vars.tilesize

fun Position.inTheWorld(): Boolean {
    if (x < -Vars.finalWorldBounds ||
        y < -Vars.finalWorldBounds
    ) return false
    if (x > Vars.world.tiles.height * Vars.tilesize + Vars.finalWorldBounds * 2 ||
        y > Vars.world.tiles.height * Vars.tilesize + Vars.finalWorldBounds
    ) return false
    return true
}