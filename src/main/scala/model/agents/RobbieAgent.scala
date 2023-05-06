package model.agents

import model.Encodable

case class RobbieAgent(chromosone: RobbieChromosone) extends Encodable {
  override def encoding: String = chromosone.encoding
}