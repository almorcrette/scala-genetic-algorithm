package model

import BoardPosition._

class Food(positions: IndexedSeq[Tile]) {
  infix def collectFrom(position: Tile): Food =
    if positions.contains(position) then new Food(positions.filterNot(_ == position))
    else this

  def amountRemaining: Int = positions.length
  
  def on(robbiePosition: RobbiePosition): Boolean = positions.contains(Tile(robbiePosition))

  override def toString: String = s"Food(positions: $positions"
}

