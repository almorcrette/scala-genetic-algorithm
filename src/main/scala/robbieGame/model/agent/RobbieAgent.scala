package robbieGame.model.agent

import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter
import model.Encodable
import model.agents.{Agent, InputOutputMapping}
import robbieGame.model.trial.{Action, Surroundings}

case class RobbieAgent(genes: RobbieGenotype) extends Agent[Surroundings, Action] {
  given inputOutputMapping: InputOutputMapping with
    lazy val lookUp: Seq[String] = Surroundings.allCodes

  def decideAction(surroundings: Surroundings): Action = {
    genes.instructionsFor(surroundings)
  }

}

object RobbieAgent {
  def apply(): RobbieAgent = RobbieAgent(RobbieGenotype.create)

}