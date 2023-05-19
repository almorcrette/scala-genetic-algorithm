package robbieGame

import robbieGame.model.agent.RobbieAgent
import robbieGame.model.trial.{BoardDimensions, RobbieGameState, TrialResults}

object RobbieGameService {

  def trials(numTrials: Int, agent: RobbieAgent): TrialResults = {
    (1 to numTrials).map { _ =>
      val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.5)

      val endState =
        (0 to 50).foldLeft(newGame: RobbieGameState)((gameState: RobbieGameState, gameTurn: Int) => {
          val action = agent.decideAction(gameState.robbieSurroundings)
          gameState.playTurn(action)
        })
      endState.points.value
    }
  }

}
