import model.trial.Action.{DoNothing, GatherFood, MoveEast, MoveNorth, MoveSouth, MoveWest, RandomAction}
import model.trial.{Action, RobbieGameState}

import scala.io.StdIn.readLine

object CommandLineInterface {
  object Input {

    def whatAction(): Action =
      Output.whatActionInstruction()
      val input = readLine()
      input match {
          case "n" => MoveNorth
          case "e" => MoveEast
          case "s" => MoveSouth
          case "w" => MoveWest
          case "f" => GatherFood
          case "o" => DoNothing
          case "r" => RandomAction
          case _ => RandomAction
      }

  }

  object Output {
    def whatActionInstruction(): Unit =
      println(
        s"""what's your move?
           |                  n: move north
           |w: move west      f: gather food   e: move east
           |                  s move south
           |         o do nothing      r random action
        """.stripMargin
      )

    def action(action: Action): Unit =
      println(s"action: ${action.asString}")


    def gameStateAtTurn(turn: Int, gameState: RobbieGameState): Unit =
      println(s"turn $turn: $gameState")

    def endGameState(gameState: RobbieGameState): Unit =
      println(s"end game: $gameState")

    def gameSummary(gameState: RobbieGameState): Unit =
      println(s"END OF GAME - food remaining : ${gameState.food.amountRemaining}. points : ${gameState.points.value}")

  }
}
