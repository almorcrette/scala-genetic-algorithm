package model

enum Feature {
  case Food, Boundary, Nothing
}

object Feature {
  def here(boardPosition: BoardPosition)
          (using boardDimensions: BoardDimensions, food: Food): Feature =
    if food.on(boardPosition) then Feature.Food
    
    else if boardPosition.xCoordinate < 1 ||
      boardPosition.yCoordinate < 1 ||
      boardPosition.xCoordinate > boardDimensions.width ||
      boardPosition.yCoordinate > boardDimensions.height
    then Feature.Boundary
   
    else Feature.Nothing
    
}