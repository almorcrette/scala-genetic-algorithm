package model

import model.BoardPosition.{RobbiePosition, Tile}
import model.Food
import model.Action.*
import model.RobbieGameState.EndOfTurn
import model.Direction.*

import scala.language.postfixOps

trait RobbieGameState {
  val boardDimensions: BoardDimensions
  val food: Food
  val robbiePosition: RobbiePosition
  val points: Points

  def playTurn(action: Action)(implicit boardDimensions: BoardDimensions): RobbieGameState.EndOfTurn =
    action match {
      case MoveNorth => playMoveAction(North)
      case MoveSouth => playMoveAction(South)
      case MoveEast => playMoveAction(East)
      case MoveWest => playMoveAction(West)
      case GatherFood => playGatherFoodAction
      case DoNothing => playDoNothingAction
      case RandomMove => playTurn(randomiseAction)
    }

  override def toString: String =
    s"model.RobbieGame(robbiePosition: $robbiePosition, points: $points, foodRemaining: ${food.amountRemaining})"

  private def playMoveAction(direction: Direction)(implicit boardDimensions: BoardDimensions): EndOfTurn =
    val newPosition = robbiePosition move direction
    val newPoints = if newPosition != robbiePosition then points.scoreMoveSuccess else points.scoreMoveFail
    EndOfTurn(food, robbiePosition move direction, newPoints, boardDimensions)

  private def playGatherFoodAction(implicit boardDimensions: BoardDimensions): EndOfTurn =
    val newPoints = if food.on(robbiePosition) then points.scoreGatherFoodSuccess else points.scoreGatherFoodFail
    EndOfTurn(food collectFrom Tile(robbiePosition), robbiePosition, newPoints, boardDimensions)

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

    val points: Points = Points(0)
  }

  case class EndOfTurn(
                        food: Food,
                        robbiePosition: RobbiePosition,
                        points: Points,
                        boardDimensions: BoardDimensions
                      ) extends RobbieGameState

  object EndOfTurn {
    def apply(gs: RobbieGameState): EndOfTurn =
      EndOfTurn(gs.food, gs.robbiePosition, gs.points, gs.boardDimensions)
  }


}