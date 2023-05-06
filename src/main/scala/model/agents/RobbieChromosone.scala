package model.agents

import model.trial.{Action, Surroundings}

import scala.util.Random

case class RobbieChromosone(sequence: String) extends Chromosone[Surroundings, Action] {
  override def encoding: String = sequence
}

object RobbieChromosone {
  def create: RobbieChromosone = {
    val alleles: List[Action] = Action.values.toList
    def randomAllele: Action = Random.shuffle(alleles).head
    val numGenes = Math.pow(alleles.length, Surroundings.size).toInt
    RobbieChromosone((1 to numGenes).foldLeft[String]("")((stem, _) => stem + randomAllele.encoding))
  }
}
