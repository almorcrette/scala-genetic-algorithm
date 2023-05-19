package generations

import model.agents.{RobbieAgent, RobbieGenotype}
import selection.Agent

object GenerationsService {
  def newPopulation(size: Int): Population =
    Population((1 to size).map(_ => RobbieAgent(RobbieGenotype.create)))

  def runGeneration(population: Population): GenerationResults = ???

  def nextGenPopulation(previousGenResults: GenerationResults): Population = ???

  def main(args: Array[String]): Unit = {
    println(newPopulation(10))
  }
}

case class Population(individuals: Seq[RobbieAgent])

case class LifeResults(agent: Agent, lifeScore: Int)

case class GenerationResults(individualResults: Seq[LifeResults])


