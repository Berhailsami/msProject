import core.domain.use_case.IteratedPrisonersDilemmaUseCase

fun main() {

    println("--- Testing Only with 30 random players ---")
    println("The result should be approximatively 29 x 27.5 = 797.5")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 0,
        numRandoms = 30,
        numTFTs = 0
    )

    println("=========================================================")
    println("========== Question 1: Maximize Your Score ==============")
    println("=========================================================")
    println("Scenario a) 5x Cooperator, 5x Defector, 19x Random")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 5,
        numRandoms = 19,
        numTFTs = 0
    )
    println("Scenario b) 8x Cooperator, 7x Defector, 14x TFT")
    runAndAnalyze(
        numCooperators = 8,
        numDefectors = 7,
        numRandoms = 0,
        numTFTs = 14
    )

    println("=========================================================")
    println("======== Question 2: Most Effective Strategy ============")
    println("=========================================================")
    println("a) Majority are Defectors ")
    runAndAnalyze(
        numCooperators = 3,
        numDefectors = 20,
        numRandoms = 3,
        numTFTs = 3
    )
    println("b) Majority are TFTs ")
    runAndAnalyze(
        numCooperators = 3,
        numDefectors = 3,
        numRandoms = 3,
        numTFTs = 20
    )

    println("=========================================================")
    println("======== Question 3: Best Strategy vs Random ============")
    println("=========================================================")
    println("Playing a 1v1 contest against a Random player (simulated by a tournament of 1 of your type vs 29 Randoms)")
    println("As a Cooperator:")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 0,
        numRandoms = 29,
        numTFTs = 0
    )
    println("As a Defector:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 1,
        numRandoms = 29,
        numTFTs = 0
    )
    println("As a TFT:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 0,
        numRandoms = 29,
        numTFTs = 1
    )

    println("================================================================")
    println("==== Question 4: When is it Better to Cooperate than Defect? ===")
    println("================================================================")
    println("A scenario where Cooperators outperform Defectors is when the population is dominated by TFT players.")
    println("Let's test a scenario with 25 TFT players, 2 Cooperators, and 2 Defectors.")
    runAndAnalyze(
        numCooperators = 2,
        numDefectors = 2,
        numRandoms = 0,
        numTFTs = 25
    )

    println("================================================================")
    println("==== Extra Credit: Testing the TFTT Strategy ===")
    println("================================================================")

    println("Scenario 1: Balanced Environment (5 of each type, including TFTT)")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 5,
        numRandoms = 5,
        numTFTs = 5,
        numTFTTs = 5
    )

    println("Scenario 2: Majority of Defectors (15 Defectors, 3 of each other type)")
    runAndAnalyze(
        numCooperators = 3,
        numDefectors = 15,
        numRandoms = 3,
        numTFTs = 3,
        numTFTTs = 3
    )

    println("Scenario 3: Majority of Cooperative types (10 Cooperators, 10 TFTs, 3 of each other)")
    runAndAnalyze(
        numCooperators = 10,
        numDefectors = 3,
        numRandoms = 3,
        numTFTs = 3,
        numTFTTs = 10
    )

    println("Scenario 4: Majority of Random players (15 Random, 3 of each other)")
    runAndAnalyze(
        numCooperators = 3,
        numDefectors = 3,
        numRandoms = 15,
        numTFTs = 3,
        numTFTTs = 3
    )
}

fun runAndAnalyze(
    numCooperators: Int,
    numDefectors: Int,
    numRandoms: Int,
    numTFTs: Int,
    numTFTTs: Int = 0
) {
    val result = IteratedPrisonersDilemmaUseCase()
        .invoke(
            numCooperators,
            numDefectors,
            numRandoms,
            numTFTs,
            numTFTTs
        )
    println("Average Scores:")
    result.averageScores.entries.sortedByDescending{ score ->
        score.value
    }.forEach{ (strategy, score) ->
        println("   - $strategy: %.2f".format(score))
    }
    println("---------------------------------------------------------")
}
