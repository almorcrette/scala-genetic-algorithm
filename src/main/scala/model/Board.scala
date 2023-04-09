package model

import model.BoardPosition._

import java.lang.Math.round
import scala.util.Random.shuffle

case class Board(tiles: IndexedSeq[Tile]) {
  private def size: Int = tiles.length

  def selectRandomFoodTiles(proportion: Float): Food =
    if (proportion < 0 || proportion > 1) {
      throw new IllegalArgumentException("proportion of food tiles must be between 0 and 1")
    } else {
      new Food(
        positions =
          shuffle(tiles)
            .take(round(this.size * proportion))
      )
    }
}

object Board {
  def apply(boardDimensions: BoardDimensions): Board =
    new Board(tiles = for {
      x <- 1 to boardDimensions.width
      y <- 1 to boardDimensions.height
    } yield BoardPosition.Tile(x, y))
}
