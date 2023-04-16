package model

case class Surroundings(
                  here: Feature,
                  north: Feature,
                  east: Feature,
                  south: Feature,
                  west: Feature
                  ) {
  override def toString: String =
    s"""Surroundings:
       |                 north: $north
       |west: $west      here: $here      east: $here
       |                 south: $south
    """.stripMargin
}
