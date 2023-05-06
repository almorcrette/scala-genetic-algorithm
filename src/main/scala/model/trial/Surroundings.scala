package model.trial

import model.trial.{Feature, ImmediateNoDiagonalBoardContext}
import model.{Encodable, EnumerableCode}

trait Environment

case class Surroundings(
                  here: Feature,
                  north: Feature,
                  east: Feature,
                  south: Feature,
                  west: Feature
                  ) extends Environment with ImmediateNoDiagonalBoardContext with Encodable {

  override def toString: String = {
    s"""Surroundings:
       |                 north: $north
       |west: $west      here: $here      east: $east
       |                 south: $south
    """.stripMargin
  }

  val encoding: String = {
    here.encoding + north.encoding + east.encoding + south.encoding + west.encoding
  }
}

object Surroundings extends ImmediateNoDiagonalBoardContext with EnumerableCode {
  lazy val allCodes: Seq[String] = {
    val featureCodes = Feature.allCodes
    for {
      here <- featureCodes
      north <- featureCodes
      east <- featureCodes
      south <- featureCodes
      west <- featureCodes
    } yield here + north + east + south + west
  }

}

