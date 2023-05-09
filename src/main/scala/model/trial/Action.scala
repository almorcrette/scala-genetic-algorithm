package model.trial

import model.agents.Allele
import model.trial.Action
import model.{Decodable, Encodable}

import scala.util.Random.shuffle

enum Action (val asString: String) extends Allele with Encodable {
  case MoveNorth extends Action("move north")
  case MoveSouth extends Action("move south")
  case MoveEast extends Action("move east")
  case MoveWest extends Action("move west") 
  case GatherFood extends Action("gather food")
  case DoNothing extends Action("do nothing")
  case RandomAction extends Action("random action")

  override def encoding: String = this match
    case MoveNorth => "N"
    case MoveSouth => "S"
    case MoveEast => "E"
    case MoveWest => "W"
    case GatherFood => "F"
    case DoNothing => "X"
    case RandomAction => "R"
}

object Action extends Decodable[Action] {
  private lazy val allNonRandomActions: IndexedSeq[Action] =
    Action
      .values
      .filterNot(_ == RandomAction)

  def randomiseAction: Action =
    shuffle(allNonRandomActions).head
    
  def encodedValues: Seq[Char] = "NSEWFXR".toList

  def decoded(string: String): Action = string match
    case "N" => MoveNorth
    case "S" => MoveSouth
    case "E" => MoveEast
    case "W" => MoveWest
    case "F" => GatherFood
    case "X" => DoNothing
    case "R" => RandomAction
    case _ => throw new Exception("Cannot decode action")


}