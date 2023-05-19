package model.agents

import model.agents.Input
import model.Encodable

trait Genotype[I <: Input, O <: Output] extends Encodable {
  val sequence: String

  def instructionsFor(g: I)(using InputOutputMapping): O
}
