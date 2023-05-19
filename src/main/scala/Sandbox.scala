
import CommandLineInterface.Output
import generations.GenerationsService
import generations.model.Generation
import robbieGame.model.trial.Feature.*
import model.agents.InputOutputMapping
import robbieGame.model.agent.{RobbieAgent, RobbieGenotype}
import robbieGame.model.trial.{BoardDimensions, RobbieGameState, Surroundings}

object Sandbox {
  def main(args: Array[String]): Unit = {

    val firstPopulation = GenerationsService.firstGeneration(100)

    def runOneIteration(population: Generation): Generation = {
      import GenerationsService._
      val res = generationResults(population, 100)
      println(res.map(_.lifeScore).sorted.take(20).sum)
      nextGeneration(res, 0.1, 0.1)
    }

    def runMultipleIterations(population: Generation, numIterations: Int): Generation =
      def runMultipleIterationsAcc(population: Generation, numIterations: Int, acc: Int): Generation =
        if (acc == numIterations) then population
        else runMultipleIterationsAcc(runOneIteration(population), numIterations, acc + 1)
      runMultipleIterationsAcc(population, numIterations, 0)

    runMultipleIterations(firstPopulation, 100)
  }
}
