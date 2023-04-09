package model

import Tile._

trait RobbieGameState {
  val boardWidth: Int
  val boardHeight: Int
  val food: Food
  val robbiePosition: RobbiePosition
  def playTurn(action: Action): RobbieGameState.EndOfTurn = ???
  
  override def toString: String = s"model.RobbieGame(boardWidth: $boardWidth, boardHeight: $boardHeight, food: $food"

}

object RobbieGameState {

  class Start(val boardWidth: Int, val boardHeight: Int, foodProportion: Float) extends RobbieGameState {
    val food: Food = Board(boardWidth, boardHeight).selectRandomFoodTiles(foodProportion)
    val robbiePosition: RobbiePosition = RobbiePosition.randomStart(boardWidth, boardHeight)
  }

  class EndOfTurn(val boardWidth: Int, val boardHeight: Int) extends RobbieGameState {
    val food: Food = ???
    val robbiePosition: Tile.RobbiePosition = ???
  }
}