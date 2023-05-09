package model.agents

import java.util.UUID

trait Agent[G <: Gene, A<: Allele] {
  val id: UniqueId = UniqueId()
//  val appeared: Id[Generation]
  val genes: Genotype[G, A]
}