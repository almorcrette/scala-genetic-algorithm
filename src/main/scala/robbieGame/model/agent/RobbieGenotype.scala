package robbieGame.model.agent

import model.agents.{Genotype, InputOutputMapping}
import robbieGame.model.trial.{Action, Feature, Surroundings}

import scala.util.Random

case class RobbieGenotype(sequence: String) extends Genotype[Surroundings, Action] {
  override def encoding: String = sequence
  
  def instructionsFor(surroundings: Surroundings)(using mapping: InputOutputMapping): Action = Action.decoded(
    sequence(mapping.lookUp.indexOf(surroundings.encoding)).toString)
}

object RobbieGenotype {
  def create: RobbieGenotype = {
    val alleles: List[Action] = Action.values.toList
    def randomAllele: Action = Random.shuffle(alleles).head
    val numGenes = Math.pow(Feature.values.length, Surroundings.size).toInt
    RobbieGenotype((1 to numGenes).foldLeft[String]("")((stem, _) => stem + randomAllele.encoding))
  }
}
