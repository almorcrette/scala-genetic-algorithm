package robbieGame.model.trial

import robbieGame.model.trial.Direction.*

import scala.util.Random
import scala.util.Random.shuffle


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

    def move(direction: Direction)(implicit boardDimensions: BoardDimensions): RobbiePosition = {

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
    
    def toTheNorth: Tile =
      Tile(xCoordinate, yCoordinate - 1)
      
    def toTheSouth: Tile =
      Tile(xCoordinate, yCoordinate + 1)

    def toTheEast: Tile =
      Tile(xCoordinate + 1, yCoordinate)

    def toTheWest: Tile =
      Tile(xCoordinate - 1, yCoordinate)
      
  }

  object RobbiePosition {
    def randomStart(boardDimensions: BoardDimensions): RobbiePosition = {
      val randomTile = shuffle(Board(boardDimensions).tiles).head
      RobbiePosition(randomTile.xCoordinate, randomTile.yCoordinate)
    }
  }
}