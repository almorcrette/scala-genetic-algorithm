package model

import model.BoardPosition.{RobbiePosition, Tile}

class Food(positions: IndexedSeq[Tile]) {
  infix def collectFrom(position: Tile): Food =
    if positions.contains(position) then
      new Food(positions.filterNot(_ == position))
    else this

  def amountRemaining: Int =
    positions.length
  
  def on(boardPosition: BoardPosition): Boolean =
    positions.contains(boardPosition)

  override def toString: String =
    s"Food(positions: $positions"
}

