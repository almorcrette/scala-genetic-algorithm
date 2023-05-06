
import CommandLineInterface.Output
import model.trial.Feature.*
import model.agents.{RobbieAgent, RobbieGenotype, SenseMaker}
import model.trial.{BoardDimensions, RobbieGameState, Surroundings}

object Sandbox {
  def main(args: Array[String]): Unit = {

    (1 to 100).foreach { agent =>
      val robbieAgent = RobbieAgent(RobbieGenotype.create)

      given robbieSenseMaker: SenseMaker with
        lazy val lookUp: Seq[String] = Surroundings.allCodes

      val averageScore = (1 to 50).map { game =>
        val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.4)

        val endState =
          (0 to 40).foldLeft(newGame: RobbieGameState)((gameState: RobbieGameState, gameTurn: Int) => {
            //          Output.gameStateAtTurn(gameTurn, gameState)
            val action = robbieAgent.decideAction(gameState.robbieSurroundings)
            //          Output.action(action)
            gameState.playTurn(action)
          })
        endState.points.value
      }.sum / 50

      println(s"agent $agent - average score : $averageScore")

    }





  }
}
