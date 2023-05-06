package model.trial

import model.trial.{BoardDimensions, BoardPosition}
import model.Encodable
import model.trial.Feature

enum Feature extends Encodable {
  case Food, Boundary, Nothing

  def encoding: String = this match
    case Food => "F"
    case Boundary => "B"
    case Nothing => "N"
}

object Feature {
  def here(boardPosition: BoardPosition)(using boardDimensions: BoardDimensions, food: Food): Feature =
    if food.on(boardPosition) then Feature.Food
    else if outOfBounds(boardPosition) then Feature.Boundary
    else Feature.Nothing

  private def outOfBounds(boardPosition: BoardPosition)(using boardDimensions: BoardDimensions): Boolean =
    boardPosition.xCoordinate < 1 ||
      boardPosition.yCoordinate < 1 ||
      boardPosition.xCoordinate > boardDimensions.width ||
      boardPosition.yCoordinate > boardDimensions.height
    
}