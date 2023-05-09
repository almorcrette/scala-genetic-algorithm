package selection

import scala.annotation.tailrec
import scala.util.Random

case class Agent(id: String, lifeScore: Int)
case class Generation(individuals: Seq[Agent])

object SelectionService {

  def elite(generation: Generation, proportionOfGeneration: Float): Seq[Agent] =
    generation.individuals
      .sortBy(_.lifeScore)(Ordering[Int].reverse)
      .take((proportionOfGeneration * generation.individuals.length).toInt)

  def selectByRouletteWheel(generation: Generation, selectNumber: Int): Seq[Agent] =
    @tailrec
    def selectOne(generation: Generation, selector: Int): Agent =
      val firstIndividual = generation.individuals.head
      if firstIndividual.lifeScore > selector then firstIndividual
      else selectOne(Generation(generation.individuals.tail), selector - generation.individuals.head.lifeScore)

    val totalExpectedValue = generation.individuals.map(_.lifeScore).sum
    (1 to selectNumber).map(_ => selectOne(generation, Random.nextInt(totalExpectedValue)))


  def main(args: Array[String]): Unit = {
    println(Random.nextInt(2))

    val generation = Generation(Seq(
      Agent("1", 64),
      Agent("2", 32),
      Agent("3", 16),
      Agent("4", 8),
      Agent("5", 4),
      Agent("6", 2),
      Agent("7", 1),
      Agent("1a", 64),
      Agent("2a", 32),
      Agent("3a", 16),
      Agent("4a", 8),
      Agent("5a", 4),
      Agent("6a", 2),
      Agent("7a", 1)
    ))

    println(elite(generation, 0.2))

    println(selectByRouletteWheel(generation, 14))
  }

}
