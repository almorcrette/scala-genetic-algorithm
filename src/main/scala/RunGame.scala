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
}
