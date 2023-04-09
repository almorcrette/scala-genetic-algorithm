package model

import model.Tile.FoodTile

import java.lang.Math.round
import scala.util.Random.shuffle

case class Board(tiles: IndexedSeq[Tile.EmptyTile]) {
  private def size: Int = tiles.length

  def selectRandomFoodTiles(proportion: Float): Food =
    if (proportion < 0 || proportion > 1) {
      throw new IllegalArgumentException("proportion of food tiles must be between 0 and 1")
    } else {
      new Food(
        shuffle(tiles)
          .take(round(this.size * proportion))
          .map(FoodTile(_))
      )
    }
}

object Board {
  def apply(xSize: Int, ySize: Int): Board =
    new Board(tiles = for {
      x <- 1 to xSize
      y <- 1 to ySize
    } yield Tile.EmptyTile(x, y))
}
