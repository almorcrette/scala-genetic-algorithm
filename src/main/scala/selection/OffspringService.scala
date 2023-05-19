package selection

import model.agents.{Output, Input, Genotype}
import robbieGame.model.agent.{RobbieAgent, RobbieGenotype}
import robbieGame.model.trial.Action

import scala.annotation.tailrec
import scala.util.Random

object OffspringService {
  
  def mutatePopulation(proportionMutate: Float, population: Seq[RobbieGenotype]): Seq[RobbieGenotype] = {
    @tailrec
    def mutateAgentsRecursive(population: Seq[RobbieGenotype], proportionMutate: Float, acc: Int): Seq[RobbieGenotype] =
      if acc == population.length * proportionMutate then population
      else mutateAgentsRecursive(mutateOne(population), proportionMutate, acc + 1)

    mutateAgentsRecursive(population, proportionMutate, 0)
  }
  
  private def mutateOne(population: Seq[RobbieGenotype]): Seq[RobbieGenotype] = {
    val shuffledPreMutate = Random.shuffle(population)
    shuffledPreMutate.tail :+ mutate(shuffledPreMutate.head, 0.01)
  }
    
    
  private def mutate(genotype: RobbieGenotype, proportionMutated: Float): RobbieGenotype = {
    
    def mutateOne(genotype: RobbieGenotype): RobbieGenotype = {
      val randomMutationLocation = Random.nextInt(genotype.sequence.length)
      val alleleAtLocation = genotype.sequence(randomMutationLocation)
      val otherAlleles: Seq[Char] = Action.encodedValues.filter(_ != alleleAtLocation)
      RobbieGenotype(genotype.sequence.updated(randomMutationLocation, Random.shuffle(otherAlleles).head))
    }
    
    @tailrec
    def mutateMultiple(genotype: RobbieGenotype, numberMutate: Int, acc: Int): RobbieGenotype = {
      if acc == numberMutate then genotype
      else mutateMultiple(mutateOne(genotype), numberMutate, acc + 1)
    }

    val numberMutated: Int = (proportionMutated * genotype.sequence.length).round
    mutateMultiple(genotype, numberMutated, 0)
  }
  
  def crossover(firstParent: RobbieGenotype, secondParent: RobbieGenotype): RobbieGenotype = {
    val randomCrossoverLocation = Random.nextInt(firstParent.sequence.length + 1)
    val lengthRightSection = firstParent.sequence.length - randomCrossoverLocation
    val firstSectionFirstParentSequence = firstParent.sequence.take(randomCrossoverLocation)
    val secondSectionSecondParentSequence = secondParent.sequence.takeRight(lengthRightSection)
    
    RobbieGenotype(firstSectionFirstParentSequence + secondSectionSecondParentSequence)
  }

}
