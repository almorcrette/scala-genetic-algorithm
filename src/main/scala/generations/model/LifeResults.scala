package generations.model

import robbieGame.model.agent.RobbieAgent
import selection.AgentResults

case class LifeResults(agent: RobbieAgent, lifeScore: Float) extends AgentResults
