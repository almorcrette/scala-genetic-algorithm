package model

import scala.util.Random.shuffle

enum Action extends Enum[Action] {
  case MoveNorth, MoveSouth, MoveEast, MoveWest, GatherFood, DoNothing, RandomMove
}

object Action {
  lazy val allNonRandomMoves: IndexedSeq[Action] =
    classOf[Action]
      .getEnumConstants
      .toIndexedSeq
      .filterNot(_ == RandomMove)

  def randomiseAction: Action =
    val action = shuffle(allNonRandomMoves).head
    println(
      s"""action: $action
       """.stripMargin
    )
    action
}