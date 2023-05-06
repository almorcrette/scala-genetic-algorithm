package model.agents

import model.agents.Gene
import model.Encodable

trait Genotype[G <: Gene, A <: Allele] extends Encodable {
  val sequence: String

  def instructionsFor(g: G)(using SenseMaker): A
}
