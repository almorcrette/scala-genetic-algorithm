import generations.EvolveStepComponent
import generations.model.Generation
import selection.{ReproductionComponent, SelectionComponent}

object Application
  extends EvolveStepComponent
    with SelectionComponent
    with ReproductionComponent {

  val selectionService: SelectionService = new SelectionService
  val reproductionService: ReproductionService = new ReproductionService
  val evolveStepService: EvolveStepService = new EvolveStepService


  def main(args: Array[String]): Unit = {
    val firstPopulation = evolveStepService.firstGeneration(100)

    def runOneIteration(population: Generation): Generation = {
      val res = evolveStepService.generationResults(population, 100)
      println(res.map(_.lifeScore).sorted.take(20).sum / 20)
      evolveStepService.nextGeneration(res, 0.1, 0.1)
    }

    def runMultipleIterations(population: Generation, numIterations: Int): Generation =
      def runMultipleIterationsAcc(population: Generation, numIterations: Int, acc: Int): Generation =
        if (acc == numIterations) then population
        else runMultipleIterationsAcc(runOneIteration(population), numIterations, acc + 1)

      runMultipleIterationsAcc(population, numIterations, 0)

    runMultipleIterations(firstPopulation, 200)
  }

}
