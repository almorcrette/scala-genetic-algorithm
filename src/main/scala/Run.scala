import model._

object Run extends App {
  import BoardPosition._
  import Direction._
  val newGame = new RobbieGameState.Start(BoardDimensions(10, 10), 0.2f)

  given BoardDimensions = newGame.boardDimensions

  val startingPosition = RobbiePosition(1, 1)

  println(s"starting position: $startingPosition")

  println(s"move down from starting position: ${startingPosition.move(Down)}")
  println(s"move right from starting position: ${startingPosition.move(Right)}")
  println(s"attempt move up from starting position: ${startingPosition.move(Up)}")
  println(s"attempt move left from starting position: ${startingPosition.move(Left)}")

  println(
    s"""move down, right, up, then left:
       |starting position: $startingPosition
       |down: ${startingPosition.move(Down)}
       |right: ${startingPosition.move(Down).move(Right)}
       |up: ${startingPosition.move(Down).move(Right).move(Up)}
       |left: ${startingPosition.move(Down).move(Right).move(Up).move(Left)}
       |""".stripMargin)

  val startingPositionBottomRight = RobbiePosition(10, 10)

  println(s"new starting position: $startingPositionBottomRight")
  println(s"attempt move down from new starting position: ${startingPositionBottomRight.move(Down)}")
  println(s"attempt move right from new starting position: ${startingPositionBottomRight.move(Right)}")




}
