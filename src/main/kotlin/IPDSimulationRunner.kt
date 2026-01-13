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

    println("--- Testing Only with 15 random players & 15 TFT---")
    println("The result should be approximatively for TFT  14 x 40 + 15 x 26.75 = 961.25")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 0,
        numRandoms = 15,
        numTFTs = 15
    )

    println("=========================================================")
    println("========== Question 1: Maximize Your Score ==============")
    println("=========================================================")
    println("=============================== Scenario a) 5x Cooperator, 5x Defector, 19x Random")
    println("As a Cooperator:")
    runAndAnalyze(
        numCooperators = 6,
        numDefectors = 5,
        numRandoms = 19,
        numTFTs = 0
    )
    println("As a Defector:")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 6,
        numRandoms = 19,
        numTFTs = 0
    )
    println("As a Random:")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 5,
        numRandoms = 20,
        numTFTs = 0
    )
    println("As a TFT:")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 5,
        numRandoms = 19,
        numTFTs = 1
    )
    println("=============================== Scenario b) 8x Cooperator, 7x Defector, 14x TFT")
    println("As a Cooperator:")
    runAndAnalyze(
        numCooperators = 9,
        numDefectors = 7,
        numRandoms = 0,
        numTFTs = 14
    )
    println("As a Defector:")
    runAndAnalyze(
        numCooperators = 8,
        numDefectors = 8,
        numRandoms = 0,
        numTFTs = 14
    )
    println("As a Random:")
    runAndAnalyze(
        numCooperators = 8,
        numDefectors = 7,
        numRandoms = 1,
        numTFTs = 14
    )
    println("As a TFT:")
    runAndAnalyze(
        numCooperators = 8,
        numDefectors = 7,
        numRandoms = 0,
        numTFTs = 15
    )

    println("=========================================================")
    println("======== Question 2: Most Effective Strategy ============")
    println("=========================================================")
    println("=============================== a) Majority are Defectors ")
    println("As a Cooperator:")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 29,
        numRandoms = 0,
        numTFTs = 0
    )
    println("As a Defector:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 30,
        numRandoms = 0,
        numTFTs = 0
    )
    println("As a Random:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 29,
        numRandoms = 1,
        numTFTs = 0
    )
    println("As a TFT:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 29,
        numRandoms = 0,
        numTFTs = 1
    )
    println("=============================== b) Majority are TFTs ")
    println("As a Cooperator:")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 0,
        numRandoms = 0,
        numTFTs = 29
    )
    println("As a Defector:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 1,
        numRandoms = 0,
        numTFTs = 29
    )
    println("EXAMPLE:")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 14,
        numRandoms = 0,
        numTFTs = 15
    )
    println("EXAMPLE 2:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 15,
        numRandoms = 0,
        numTFTs = 15
    )
    println("EXAMPLE 3:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 14,
        numRandoms = 1,
        numTFTs = 15
    )
    println("EXAMPLE 4:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 14,
        numRandoms = 0,
        numTFTs = 16
    )
    println("As a Random:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 0,
        numRandoms = 1,
        numTFTs = 29
    )
    println("As a TFT:")
    runAndAnalyze(
        numCooperators = 0,
        numDefectors = 0,
        numRandoms = 0,
        numTFTs = 30
    )

    println("=========================================================")
    println("======== Question 3: Best Strategy vs Random ============")
    println("=========================================================")
    println("Playing a 1v1 contest against 29 Random player")
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
        numCooperators = 1,
        numDefectors = 1,
        numRandoms = 0,
        numTFTs = 28
    )

    println("================================================================")
    println("==== Extra Credit: Testing the TFTT Strategy ===")
    println("================================================================")


    println("Scenario 1: Balanced Environment (6 of each type, including TFTT)")
    runAndAnalyze(
        numCooperators = 5,
        numDefectors = 5,
        numRandoms = 5,
        numTFTs = 5,
        numTFTTs = 5,
        numPunishers = 5,
    )

    println("Scenario 2: Majority of Defectors")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 25,
        numRandoms = 1,
        numTFTs = 1,
        numTFTTs = 1,
        numPunishers = 1,
    )

    println("Scenario 3: Majority of Cooperative types")
    runAndAnalyze(
        numCooperators = 25,
        numDefectors = 1,
        numRandoms = 1,
        numTFTs = 1,
        numTFTTs = 1,
        numPunishers = 1,
    )

    println("Scenario 4: Majority of Random players")
    runAndAnalyze(
        numCooperators = 1,
        numDefectors = 1,
        numRandoms = 25,
        numTFTs = 1,
        numTFTTs = 1,
        numPunishers = 1,
    )
}

fun runAndAnalyze(
    numCooperators: Int,
    numDefectors: Int,
    numRandoms: Int,
    numTFTs: Int,
    numTFTTs: Int = 0,
    numPunishers: Int = 0,
) {
    val result = IteratedPrisonersDilemmaUseCase()
        .invoke(
            numCooperators,
            numDefectors,
            numRandoms,
            numTFTs,
            numTFTTs,
            numPunishers,
        )
    println("Average Scores:")
    result.averageScores.entries.sortedByDescending{ score ->
        score.value
    }.forEach{ (strategy, score) ->
        println("   - $strategy: %.2f".format(score))
    }
    println("---------------------------------------------------------")
}
