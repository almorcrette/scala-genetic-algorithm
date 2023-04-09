import RobbieGameSetup.emptyBoard

import scala.annotation.tailrec
import scala.math.BigDecimal.RoundingMode.HALF_UP
import scala.util.Random

trait Tile {
  val xCoordinate: Int
  val yCoordinate: Int
}

object Tile {

  case class EmptyTile(override val xCoordinate: Int, override val yCoordinate: Int) extends Tile

  case class FoodTile(override val xCoordinate: Int, override val yCoordinate: Int) extends Tile

  object FoodTile {
    def apply(emptyTile: EmptyTile): FoodTile = FoodTile(emptyTile.xCoordinate, emptyTile.yCoordinate)
  }
}

case class RobbieGame(boardWidth: Int, boardHeight: Int, food: IndexedSeq[Tile.FoodTile])


object RobbieGameSetup {
  import Tile._
  import Random._
  import Math._

  def emptyBoard(xSize: Int, ySize: Int): IndexedSeq[EmptyTile] = for {
    x <- 1 to xSize
    y <- 1 to ySize
  } yield EmptyTile(x, y)

  def foodTiles(emptyBoard: IndexedSeq[EmptyTile], proportion: Float): IndexedSeq[FoodTile] =
    if (proportion < 0 || proportion > 1) {
      throw new IllegalArgumentException("proportion of food tiles must be between 0 and 1")
    } else {
      shuffle(emptyBoard)
        .take(round(emptyBoard.length * proportion))
        .map(FoodTile(_))
    }

  def gameSetup(boardWidth: Int, boardHeight: Int, foodProportion: Float): RobbieGame = {
    RobbieGame(boardWidth, boardHeight, foodTiles(emptyBoard(boardWidth, boardHeight), foodProportion))
  }

  def main(args: Array[String]): Unit = {
    println(gameSetup(10, 10, 0.2))
  }
}
