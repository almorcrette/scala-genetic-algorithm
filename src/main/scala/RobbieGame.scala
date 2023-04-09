import model.{EmptyBoard, Food, Tile}
import model.Tile.FoodTile

import java.lang.Math.round
import scala.annotation.tailrec
import scala.math.BigDecimal.RoundingMode.HALF_UP
import scala.util.Random
import scala.util.Random.shuffle


class RobbieGame(boardWidth: Int, boardHeight: Int, food: Food) {
  override def toString: String = s"RobbieGame(boardWidth: $boardWidth, boardHeight: $boardHeight, food: $food"
}

object RobbieGame {
  def apply(boardWidth: Int, boardHeight: Int, foodProportion: Float): RobbieGame = {
    new RobbieGame(boardWidth, boardHeight, EmptyBoard(boardWidth, boardHeight).selectRandomFoodTiles(foodProportion))
  }




  def main(args: Array[String]): Unit = {
    println(RobbieGame(10, 10, 0.2f).toString)
  }
}
