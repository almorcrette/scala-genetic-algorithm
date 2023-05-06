
import model.Surroundings
import model.Feature.*

object Test {
  def main(args: Array[String]): Unit = {
    val surroundings = Surroundings(
      Nothing,
      Nothing,
      Food,
      Boundary,
      Boundary
    )

    println(Nothing.encoding)
    println(Food.encoding)
    println(Boundary.encoding)

    println(surroundings.encoding)

  }
}
