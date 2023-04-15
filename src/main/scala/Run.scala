import model.*
import model.Action._
import model.BoardPosition.RobbiePosition
import model.Direction.*

object Run extends App {
  val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.2f)

  println(newGame)

  val endOfTurn1 = newGame
    .playTurn(MoveNorth)(newGame.boardDimensions)
  println(endOfTurn1)

  val endOfTurn2 = endOfTurn1
    .playTurn(MoveSouth)(endOfTurn1.boardDimensions)
  println(endOfTurn2)

  val endOfTurn3 = endOfTurn2
    .playTurn(GatherFood)(endOfTurn2.boardDimensions)
  println(endOfTurn3)


  val endOfTurn4 = endOfTurn3
    .playTurn(MoveEast)(endOfTurn3.boardDimensions)
  println(endOfTurn4)

  val endOfTurn5 = endOfTurn4
    .playTurn(MoveWest)(endOfTurn4.boardDimensions)
  println(endOfTurn5)

  val endOfTurn6 = endOfTurn5
    .playTurn(DoNothing)(endOfTurn5.boardDimensions)
  println(endOfTurn6)





}
