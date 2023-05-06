package model

case class Individual(chromosone: RobbieChromosone) extends Encodable {
  override def encoding: String = chromosone.encoding
}