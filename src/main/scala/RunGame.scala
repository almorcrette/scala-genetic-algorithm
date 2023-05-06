import CommandLineInterface.*
import model.trial.Action.RandomAction
import model.trial.{Action, BoardDimensions, RobbieGameState}

object RunGame {

  def main(args: Array[String]): Unit = {
    if (args.length != 4) then {
      throw new IllegalArgumentException(
        """requires four arguments:
          |1. board length,
          |2. board width,
          |3. food proportion,
          |4. number of turns""".stripMargin
      )
    } else {

      val boardLength: Int = args(0).toInt
      val boardHeight: Int = args(1).toInt
      val foodProportion: Float = args(2).toFloat
      val numberTurns: Int = args(3).toInt

      val newGame = new RobbieGameState.Start(BoardDimensions(boardLength, boardHeight), foodProportion)

      val endState =
        (0 to numberTurns).foldLeft(newGame: RobbieGameState)((gameState: RobbieGameState, gameTurn: Int) => {
          Output.gameStateAtTurn(gameTurn, gameState)
          val action = Input.whatAction()
          Output.action(action)
          gameState.playTurn(action)
        })

      Output.endGameState(endState)

    }

  }

//  val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.5f)
//
//  println(newGame)
//
//  val endOfTurn1 = newGame
//    .playTurn(MoveNorth)(newGame.boardDimensions)
//  println(endOfTurn1)
//
//  val endOfTurn2 = endOfTurn1
//    .playTurn(MoveSouth)(endOfTurn1.boardDimensions)
//  println(endOfTurn2)
//
//  val endOfTurn3 = endOfTurn2
//    .playTurn(GatherFood)(endOfTurn2.boardDimensions)
//  println(endOfTurn3)
//
//
//  val endOfTurn4 = endOfTurn3
//    .playTurn(MoveEast)(endOfTurn3.boardDimensions)
//  println(endOfTurn4)
//
//  val endOfTurn5 = endOfTurn4
//    .playTurn(MoveWest)(endOfTurn4.boardDimensions)
//  println(endOfTurn5)
//
//  val endOfTurn6 = endOfTurn5
//    .playTurn(DoNothing)(endOfTurn5.boardDimensions)
//  println(endOfTurn6)
//
//  val endOfTurn7 = endOfTurn6
//    .playTurn(RandomMove)(endOfTurn6.boardDimensions)
//  println(endOfTurn7)






}
