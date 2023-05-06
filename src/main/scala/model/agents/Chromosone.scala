package model.agents

import model.agents.Gene
import model.Encodable

trait Chromosone[G <: Gene, A <: Allele] extends Encodable {
  val sequence: String
}
