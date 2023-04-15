package model

case class Points(value: Int) {

  def scoreGatherFoodSuccess: Points =
    Points(this.value + 10)

  def scoreGatherFoodFail: Points =
    Points(this.value - 2)

  def scoreMoveSuccess: Points =
    Points(this.value + 1)

  def scoreMoveFail: Points =
    Points(this.value - 5)
}
