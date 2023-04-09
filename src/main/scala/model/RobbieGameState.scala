package model

import BoardPosition._

trait RobbieGameState {
  val boardDimensions: BoardDimensions
  val food: Food
  val robbiePosition: RobbiePosition
  val points: Int
  def playTurn(action: Action): RobbieGameState.EndOfTurn = ???

  override def toString: String = s"model.RobbieGame(boardDimensions: $boardDimensions, food: $food"
}

object RobbieGameState {

  class Start(val boardDimensions: BoardDimensions, foodProportion: Float) extends RobbieGameState {
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
}