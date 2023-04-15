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

  def playTurn(action: Action)(implicit boardDimensions: BoardDimensions): RobbieGameState.EndOfTurn =
    action match {
      case MoveNorth => playMoveAction(North)
      case MoveSouth => playMoveAction(South)
      case MoveEast => playMoveAction(East)
      case MoveWest => playMoveAction(West)
      case GatherFood => playGatherFoodAction
      case DoNothing => playDoNothingAction
    }

  override def toString: String =
    s"model.RobbieGame(robbiePosition: $robbiePosition, points: $points, foodRemaining: ${food.amountRemaining})"

  private def playMoveAction(direction: Direction)(implicit boardDimensions: BoardDimensions): EndOfTurn =
    EndOfTurn(food, robbiePosition move direction, points, boardDimensions)

  private def playGatherFoodAction: EndOfTurn =
    EndOfTurn(food collectFrom Tile(robbiePosition), robbiePosition, points, boardDimensions)

  private def playDoNothingAction: EndOfTurn =
    EndOfTurn(this)
}

object RobbieGameState {

  class Start(
               val boardDimensions: BoardDimensions,
               foodProportion: Float
             ) extends RobbieGameState {

    val food: Food =
      Board(boardDimensions).selectRandomFoodTiles(foodProportion)

    val robbiePosition: RobbiePosition =
      RobbiePosition.randomStart(boardDimensions)

    val points: Int = 0
  }

  case class EndOfTurn(
                        food: Food,
                        robbiePosition: RobbiePosition,
                        points: Int,
                        boardDimensions: BoardDimensions
                      ) extends RobbieGameState

  object EndOfTurn {
    def apply(gs: RobbieGameState): EndOfTurn =
      EndOfTurn(gs.food, gs.robbiePosition, gs.points, gs.boardDimensions)
  }


}