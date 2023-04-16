package model

import scala.util.Random.shuffle

enum Action (val asString: String)  extends Enum[Action] {
  case MoveNorth extends Action("move north")
  case MoveSouth extends Action("move south")
  case MoveEast extends Action("move east")
  case MoveWest extends Action("move west") 
  case GatherFood extends Action("gather food")
  case DoNothing extends Action("do nothing")
  case RandomAction extends Action("random action")

}

object Action {
  private lazy val allNonRandomActions: IndexedSeq[Action] =
    classOf[Action]
      .getEnumConstants
      .toIndexedSeq
      .filterNot(_ == RandomAction)

  def randomiseAction: Action =
    val action = shuffle(allNonRandomActions).head
    println(
      s"""action: $action
       """.stripMargin
    )
    action
}