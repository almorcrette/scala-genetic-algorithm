package model

import scala.util.Random
import scala.util.Random.shuffle

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

  case class RobbiePosition(override val xCoordinate: Int, override val yCoordinate: Int) extends Tile {

    def move(direction: Direction)(implicit robbieGame: RobbieGameState): RobbiePosition = {
      import Direction._

      direction match
        case Up => if yCoordinate == 1 then this else
          RobbiePosition(xCoordinate, yCoordinate -1)

        case Down => if yCoordinate == robbieGame.boardHeight then this else
          RobbiePosition(xCoordinate, yCoordinate + 1)

        case Left => if xCoordinate == 1 then this else
          RobbiePosition(xCoordinate - 1, yCoordinate)

        case Right => if xCoordinate == robbieGame.boardWidth then this else
          RobbiePosition(xCoordinate + 1, yCoordinate)
    }
  }

  object RobbiePosition {
    def randomStart(boardWidth: Int, boardHeight: Int): RobbiePosition = {
      val randomTile = shuffle(Board(boardWidth, boardHeight).tiles).head
      RobbiePosition(randomTile.xCoordinate, randomTile.yCoordinate)

    }

  }
}

enum Direction {
  case Up, Down, Left, Right
}