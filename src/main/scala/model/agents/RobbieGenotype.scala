package model.agents

import model.trial.{Action, Surroundings}

import scala.util.Random

case class RobbieGenotype(sequence: String) extends Genotype[Surroundings, Action] {
  override def encoding: String = sequence
  
  def instructionsFor(surroundings: Surroundings)(using senseMaker: SenseMaker): Action = Action.decoded(
    sequence(senseMaker.lookUp.indexOf(surroundings.encoding)).toString)
}

object RobbieGenotype {
  def create: RobbieGenotype = {
    val alleles: List[Action] = Action.values.toList
    def randomAllele: Action = Random.shuffle(alleles).head
    val numGenes = Math.pow(Surroundings.size, alleles.length).toInt
    RobbieGenotype((1 to numGenes).foldLeft[String]("")((stem, _) => stem + randomAllele.encoding))
  }
}
