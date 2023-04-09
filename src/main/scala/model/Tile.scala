package model

trait Tile {
  val xCoordinate: Int
  val yCoordinate: Int
}

object Tile {

  case class EmptyTile(override val xCoordinate: Int, override val yCoordinate: Int) extends Tile

  case class FoodTile(override val xCoordinate: Int, override val yCoordinate: Int) extends Tile {
  }

  object FoodTile {
    def apply(emptyTile: EmptyTile): FoodTile = FoodTile(emptyTile.xCoordinate, emptyTile.yCoordinate)
  }
}