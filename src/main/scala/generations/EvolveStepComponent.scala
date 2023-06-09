package generations

import generations.model.{Generation, GenerationResults, LifeResults}
import robbieGame.RobbieGameService
import robbieGame.model.agent.{RobbieAgent, RobbieGenotype}
import robbieGame.model.trial.{BoardDimensions, RobbieGameState, Surroundings}
import robbieGame.model.agent
import selection.{AgentResults, ReproductionComponent, SelectionComponent}

import scala.util.Random

trait EvolveStepComponent {
  this: SelectionComponent with ReproductionComponent =>

  val evolveStepService: EvolveStepService

  class EvolveStepService {

    def firstGeneration(size: Int): Generation =
      (1 to size).map(_ => RobbieAgent())

    def generationResults(generation: Generation, numTrials: Int): GenerationResults =
      generation.map { agent =>
        import robbieGame.model.trial.TrialResults.average
        LifeResults(
          agent,
          RobbieGameService
            .trials(numTrials, agent)
            .average
        )
      }


    def nextGeneration(
                        generationResults: GenerationResults,
                        proportionEliteSelection: Float,
                        proportionMutate: Float
                      ): Generation = {

      val eliteGenes: Seq[RobbieGenotype] =
        selectionService.elite(generationResults, proportionEliteSelection)
          .map(_.agent.genes)

      val parents: Seq[LifeResults] = selectionService.selectByRouletteWheel(
        generationResults.map(lifeResults => LifeResults(lifeResults.agent, lifeResults.lifeScore + 250)),
        (generationResults.length * (1 - proportionEliteSelection)).round
      )

      val childGenes: Seq[RobbieGenotype] = (0 until parents.length).map { index =>
        reproductionService.crossover(
          parents(index).agent.genes,
          parents((index + 1) % parents.length).agent.genes
        )
      }

      val nextGenGenes = reproductionService.mutatePopulation(
        proportionMutate,
        population = eliteGenes ++ childGenes
      )

      nextGenGenes.map(RobbieAgent.apply)
    }

  }

}



