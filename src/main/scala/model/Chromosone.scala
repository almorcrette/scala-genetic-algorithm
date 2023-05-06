package model

trait Chromosone[G <: Gene, A <: Allele] extends Encodable {
  val sequence: String
}
