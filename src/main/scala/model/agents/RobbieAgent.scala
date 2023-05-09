package model.agents

import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter
import model.Encodable
import model.trial.{Action, Surroundings}

case class RobbieAgent(genes: RobbieGenotype) extends Agent[Surroundings, Action] {
  def decideAction(surroundings: Surroundings)(using senseMaker: SenseMaker): Action = {
    genes.instructionsFor(surroundings)
  }
}