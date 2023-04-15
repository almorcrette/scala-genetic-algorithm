package model

import model.BoardPosition.{RobbiePosition, Tile}
import model.RobbieGameState.EndOfTurn
import model.Action.*
import Direction.*

import scala.language.postfixOps


trait RobbieGameState {
  val boardDimensions: BoardDimensions
  val food: Food
  val robbiePosition: RobbiePosition
  val points: Int

  def playTurn(action: Action)(implicit boardDimensions: BoardDimensions): RobbieGameState.EndOfTurn = action match {
    case MoveNorth => EndOfTurn(food, robbiePosition move North, points, boardDimensions)
    case MoveSouth => EndOfTurn(food, robbiePosition move South, points, boardDimensions)
    case MoveEast => EndOfTurn(food, robbiePosition move East, points, boardDimensions)
    case MoveWest => EndOfTurn(food, robbiePosition move West, points, boardDimensions)
    case GatherFood => EndOfTurn(food collectFrom Tile(robbiePosition), robbiePosition, points, boardDimensions)
    case DoNothing => EndOfTurn(this)
  }

  override def toString: String = s"model.RobbieGame(robbiePosition: $robbiePosition, points: $points, foodRemaining: ${food.amountRemaining})"
}

object RobbieGameState {

  class Start(
               val boardDimensions: BoardDimensions,
               foodProportion: Float
             ) extends RobbieGameState {
    val food: Food = Board(boardDimensions).selectRandomFoodTiles(foodProportion)
    val robbiePosition: RobbiePosition = RobbiePosition.randomStart(boardDimensions)
    val points: Int = 0
  }

  case class EndOfTurn(
                        food: Food,
                        robbiePosition: RobbiePosition,
                        points: Int,
                        boardDimensions: BoardDimensions
                      ) extends RobbieGameState

  object EndOfTurn {
    def apply(gs: RobbieGameState): EndOfTurn = EndOfTurn(gs.food, gs.robbiePosition, gs.points, gs.boardDimensions)
  }


}