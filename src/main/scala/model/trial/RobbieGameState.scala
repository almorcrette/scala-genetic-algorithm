package model.trial

import Action.*
import model.trial.BoardPosition.{RobbiePosition, Tile}
import model.trial.Direction.*
import model.trial.Feature.here
import model.trial.RobbieGameState.EndOfTurn
import model.trial.{Board, BoardDimensions, Direction, Food}
import model.trial.{Action, Points, RobbieGameState, Surroundings}

trait RobbieGameState {
  val boardDimensions: BoardDimensions
  val food: Food
  val robbiePosition: RobbiePosition
  val points: Points

  lazy val robbieSurroundings: Surroundings =
    given BoardDimensions = boardDimensions; given Food = food
    Surroundings(
      here = here(Tile(robbiePosition)),
      north = here(robbiePosition.toTheNorth),
      east = here(robbiePosition.toTheEast),
      south = here(robbiePosition.toTheSouth),
      west = here(robbiePosition.toTheWest)
    )

  def playTurn(action: Action): RobbieGameState.EndOfTurn =
    given BoardDimensions = boardDimensions
    action match {
      case MoveNorth => playMoveAction(North)
      case MoveSouth => playMoveAction(South)
      case MoveEast => playMoveAction(East)
      case MoveWest => playMoveAction(West)
      case GatherFood => playGatherFoodAction
      case DoNothing => playDoNothingAction
      case RandomAction => playTurn(randomiseAction)
    }

  override def toString: String =
    s"""
       |RobbieGameState
       | - robbiePosition: $robbiePosition
       | - surroundings: $robbieSurroundings
       | - points: $points
       | - foodRemaining: ${food.amountRemaining}
       """.stripMargin

  infix private def playMoveAction(direction: Direction): EndOfTurn =
    given BoardDimensions = boardDimensions
    val newPosition = robbiePosition move direction
    val newPoints = if newPosition != robbiePosition then points.scoreMoveSuccess else points.scoreMoveFail
    EndOfTurn(food, robbiePosition move direction, newPoints, boardDimensions)

  private def playGatherFoodAction(implicit boardDimensions: BoardDimensions): EndOfTurn =
    val newPoints = if food.on(Tile(robbiePosition)) then points.scoreGatherFoodSuccess else points.scoreGatherFoodFail
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