package robbieGame.model.trial

type TrialResults = Seq[Int]

object TrialResults {
  extension (trialResults: TrialResults)
    def average: Float = trialResults.sum / 100
}


