package selection

import model.agents.{Allele, Gene, Genotype, RobbieAgent, RobbieGenotype}
import model.trial.Action

import scala.annotation.tailrec
import scala.util.Random

object OffspringService {
  def mutate(genotype: RobbieGenotype, proportionMutated: Float): RobbieGenotype = {
    
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
  
  def crossover(firstParent: RobbieGenotype, secondParent: RobbieGenotype): (RobbieGenotype, RobbieGenotype) = {
    val randomCrossoverLocation = Random.nextInt(firstParent.sequence.length)
    val lengthRightSection = firstParent.sequence.length - randomCrossoverLocation
    val firstSectionFirstParentSequence = firstParent.sequence.take(randomCrossoverLocation)
    val secondSectionFirstParentSequence = firstParent.sequence.takeRight(lengthRightSection)
    val firstSectionSecondParentSequence = secondParent.sequence.take(randomCrossoverLocation)
    val secondSectionSecondParentSequence = secondParent.sequence.takeRight(lengthRightSection)
    
    (
      RobbieGenotype(firstSectionFirstParentSequence + secondSectionSecondParentSequence),
      RobbieGenotype(firstSectionSecondParentSequence + secondSectionFirstParentSequence)
    )
  }

}
