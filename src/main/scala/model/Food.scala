package model

import BoardPosition._

class Food(positions: IndexedSeq[Tile]) {
  override def toString: String = s"Food(positions: $positions"
}

