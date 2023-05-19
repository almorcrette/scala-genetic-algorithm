package model.agents

import java.util.UUID

trait Agent[I <: Input, O <: Output] {
  val id: UniqueId = UniqueId()
//  val appeared: Id[Generation]
  val genes: Genotype[I, O]

  given inputOutputMapping: InputOutputMapping
}