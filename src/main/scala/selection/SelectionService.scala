package selection

import scala.annotation.tailrec
import scala.util.Random

case class Agent(id: String, lifeScore: Int)

object SelectionService {

  def elite[A <: Agent](generation: Seq[A], proportionOfGeneration: Float): Seq[A] =
    generation
      .sortBy(_.lifeScore)(Ordering[Int].reverse)
      .take((proportionOfGeneration * generation.length).round)

  def selectByRouletteWheel[A <: Agent](generation: Seq[A], selectNumber: Int): Seq[A] =
    @tailrec
    def selectOne(generation: Seq[A], selector: Int): A =
      val individual = generation.head
      if individual.lifeScore > selector then individual
      else selectOne(generation.tail, selector - individual.lifeScore)

    val totalExpectedValue = generation.map(_.lifeScore).sum
    (1 to selectNumber).map(_ => selectOne(generation, Random.nextInt(totalExpectedValue)))

}
