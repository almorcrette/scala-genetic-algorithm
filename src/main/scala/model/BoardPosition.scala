package model

import scala.util.Random
import scala.util.Random.shuffle
import Direction._


trait BoardPosition {
  val xCoordinate: Int
  val yCoordinate: Int
}

object BoardPosition {
  
  case class Tile(xCoordinate: Int, yCoordinate: Int) extends BoardPosition
  
  object Tile {
    def apply(rp: RobbiePosition): Tile = Tile(rp.xCoordinate, rp.yCoordinate)
  }

  case class RobbiePosition(xCoordinate: Int, yCoordinate: Int) extends BoardPosition {

    infix def move(direction: Direction)(implicit boardDimensions: BoardDimensions): RobbiePosition = {

      direction match
        case North => if yCoordinate == 1 then this else
          RobbiePosition(xCoordinate, yCoordinate -1)

        case South => if yCoordinate == boardDimensions.height then this else
          RobbiePosition(xCoordinate, yCoordinate + 1)

        case East =>
          if xCoordinate == boardDimensions.height then this else
            RobbiePosition(xCoordinate + 1, yCoordinate)

        case West => if xCoordinate == 1 then this else
          RobbiePosition(xCoordinate - 1, yCoordinate)
    }
  }

  object RobbiePosition {
    def randomStart(boardDimensions: BoardDimensions): RobbiePosition = {
      val randomTile = shuffle(Board(boardDimensions).tiles).head
      RobbiePosition(randomTile.xCoordinate, randomTile.yCoordinate)
    }
  }
}