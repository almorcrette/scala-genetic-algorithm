package model

case class Surroundings(
                  here: Feature,
                  north: Feature,
                  east: Feature,
                  south: Feature,
                  west: Feature
                  ) extends ImmediateNoDiagonalBoardContext with Encodable {

  override def toString: String = {
    s"""Surroundings:
       |                 north: $north
       |west: $west      here: $here      east: $east
       |                 south: $south
    """.stripMargin
  }

  val encoding: String = {
    s"${here.encoding}${north.encoding}${east.encoding}${south.encoding}${west.encoding}"
  }
}

object Surroundings extends ImmediateNoDiagonalBoardContext

