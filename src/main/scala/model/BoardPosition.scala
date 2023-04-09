package model

import scala.util.Random
import scala.util.Random.shuffle

trait BoardPosition {
  val xCoordinate: Int
  val yCoordinate: Int
}

object BoardPosition {
  
  case class Tile(xCoordinate: Int, yCoordinate: Int) extends BoardPosition

  case class RobbiePosition(xCoordinate: Int, yCoordinate: Int) extends BoardPosition {

    def move(direction: Direction)(implicit boardDimensions: BoardDimensions): RobbiePosition = {
      import Direction._

      direction match
        case Up => if yCoordinate == 1 then this else
          RobbiePosition(xCoordinate, yCoordinate -1)

        case Down => if yCoordinate == boardDimensions.height then this else
          RobbiePosition(xCoordinate, yCoordinate + 1)

        case Left => if xCoordinate == 1 then this else
          RobbiePosition(xCoordinate - 1, yCoordinate)

        case Right => if xCoordinate == boardDimensions.height then this else
          RobbiePosition(xCoordinate + 1, yCoordinate)
    }
  }

  object RobbiePosition {
    def randomStart(boardDimensions: BoardDimensions): RobbiePosition = {
      val randomTile = shuffle(Board(boardDimensions).tiles).head
      RobbiePosition(randomTile.xCoordinate, randomTile.yCoordinate)

    }

  }
}

enum Direction {
  case Up, Down, Left, Right
}