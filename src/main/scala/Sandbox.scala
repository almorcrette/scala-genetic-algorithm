
import CommandLineInterface.Output
import model.trial.Feature.*
import model.agents.{RobbieAgent, RobbieGenotype, SenseMaker}
import model.trial.{BoardDimensions, RobbieGameState, Surroundings}

object Sandbox {
  def main(args: Array[String]): Unit = {
    given robbieSenseMaker: SenseMaker with
      lazy val lookUp: Seq[String] = Surroundings.allCodes

    val cohort = (1 to 200).map(agentNo =>
      RobbieAgent(RobbieGenotype.create)
    )

    val averageScores = cohort.map { agent =>
      val trials = (1 to 100).map { _ =>
        val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.4)

        val endState =
          (0 to 40).foldLeft(newGame: RobbieGameState)((gameState: RobbieGameState, gameTurn: Int) => {
            //          Output.gameStateAtTurn(gameTurn, gameState)
            val action = agent.decideAction(gameState.robbieSurroundings)
            //          Output.action(action)
            gameState.playTurn(action)
          })
        endState.points.value
      }
      trials.sum / 100
    }

    averageScores.foreach(score => println(s"average score : $score"))







  }
}
