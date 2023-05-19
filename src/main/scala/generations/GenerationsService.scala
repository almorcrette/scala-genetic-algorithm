package generations

import generations.model.{Generation, GenerationResults, LifeResults}
import robbieGame.RobbieGameService
import robbieGame.model.agent.{RobbieAgent, RobbieGenotype}
import robbieGame.model.trial.{BoardDimensions, RobbieGameState, Surroundings}
import robbieGame.model.agent
import selection.{AgentResults, OffspringService, SelectionService}

import scala.util.Random

object GenerationsService {

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
      SelectionService.elite(generationResults, proportionEliteSelection)
        .map(_.agent.genes)

    val parents: Seq[LifeResults] = SelectionService.selectByRouletteWheel(
      generationResults.map(lifeResults => LifeResults(lifeResults.agent, lifeResults.lifeScore + 250)),
      (generationResults.length * (1 - proportionEliteSelection)).round
    )

    val childGenes: Seq[RobbieGenotype] = (0 until parents.length).map { index =>
      OffspringService.crossover(
        parents(index).agent.genes,
        parents((index + 1) % parents.length).agent.genes
      )
    }

    val nextGenGenes = OffspringService.mutatePopulation(
      proportionMutate,
      population = eliteGenes ++ childGenes
    )

    nextGenGenes.map(RobbieAgent.apply)
  }

}

