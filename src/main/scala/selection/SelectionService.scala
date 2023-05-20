package selection

import scala.annotation.tailrec
import scala.util.Random

trait AgentResults {
  def lifeScore: Float
}

trait SelectionComponent {
  val selectionService: SelectionService

  class SelectionService {

    def elite[A <: AgentResults](generation: Seq[A], proportionOfGeneration: Float): Seq[A] =
      generation
        .sortBy(_.lifeScore)(Ordering[Float].reverse)
        .take((proportionOfGeneration * generation.length).round)

    def selectByRouletteWheel[A <: AgentResults](generation: Seq[A], selectNumber: Int): Seq[A] =
      @tailrec
      def selectOne(generation: Seq[A], selector: Float): A =
        val individual = generation.head
        if individual.lifeScore > selector then individual
        else selectOne(generation.tail, selector - individual.lifeScore)

      val totalExpectedValue = generation.map(_.lifeScore).sum
      (1 to selectNumber).map(_ => selectOne(generation, Random.nextLong(totalExpectedValue.toLong)))

  }
}


