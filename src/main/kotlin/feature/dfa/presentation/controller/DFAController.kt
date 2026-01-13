package feature.dfa.presentation.controller

import core.domain.model.dfa.DFA
import core.domain.model.dfa.DFAParser
import feature.dfa.presentation.model.DFAListener
import feature.dfa.presentation.model.DFAModel
import feature.dfa.presentation.view.DFAVerifierView
import javax.swing.SwingUtilities

class DFAController(
    private val model: DFAModel,
    private val view: DFAVerifierView
) {
    init {
        view.buildButton.addActionListener { onBuildClicked() }
        view.checkButton.addActionListener { onCheckClicked() }
        view.resetButton.addActionListener { onResetClicked() }
        view.loadExampleButton.addActionListener { onLoadExampleClicked() }
        model.addListener(object : DFAListener {
            override fun onDFABuilt(dfa: DFA?) {
                SwingUtilities.invokeLater {
                    view.statusLabel.text = if (dfa == null) {
                        "DFA cleared."
                    } else {
                        "DFA built successfully."
                    }
                }
            }

            override fun onResultChanged(resultText: String) {
                SwingUtilities.invokeLater { view.statusLabel.text = resultText }
            }
        })
    }

    private fun onBuildClicked() {
        try {
            val dfa = DFAParser.parse(
                statesStr = view.statesField.text,
                alphabetStr = view.alphabetField.text,
                transitionsStr = view.transitionsArea.text,
                startStateStr = view.startStateField.text,
                acceptStatesStr = view.acceptStatesField.text,
                variablesStr = view.variablesArea.text
            )
            model.setDFA(dfa)
            view.simulationArea.text = ""
            model.setResult("DFA is ready. Enter a word and click Check.")
        } catch (e: Exception) {
            model.setDFA(null)
            model.setResult("Error building DFA: ${e.message}")
        }
    }

    private fun onCheckClicked() {
        val dfa = model.dfa
        if (dfa == null) {
            model.setResult("Please build the DFA first.")
            return
        }
        val word = view.wordField.text
        val simulation = try { dfa.simulate(word) } catch (e: Exception) {
            view.simulationArea.text = "Error during simulation: ${e.message}"
            model.setResult("Error simulating word.")
            return
        }

        val sb = StringBuilder()
        sb.appendLine("Start at ${dfa.startState}")
        if (word.isEmpty()) {
            sb.appendLine("(empty word)")
        }
        simulation.steps.forEachIndexed { idx, step ->
            sb.appendLine("${idx + 1}. δ(${step.from}, '${step.symbol}') -> ${step.to}")
        }
        sb.appendLine("Final state: ${simulation.finalState}")
        sb.append("Verdict: ")
        sb.append(if (simulation.accepted) "ACCEPTED" else "REJECTED")
        if (!simulation.accepted && simulation.steps.size < word.length) {
            val invalidSymbol = word.any { it !in dfa.alphabet }
            if (invalidSymbol) {
                sb.appendLine()
                sb.append("Reason: input contains symbols not in Σ = ")
                sb.append(dfa.alphabet.joinToString(prefix = "{", postfix = "}"))
            } else {
                val nextIndex = simulation.steps.size
                val failingPrefixState = if (simulation.steps.isEmpty()) dfa.startState else simulation.steps.last().to
                val failingSymbol = word.getOrNull(nextIndex)
                sb.appendLine()
                sb.append("Reason: missing transition δ($failingPrefixState, '$failingSymbol')")
            }
        }

        view.simulationArea.text = sb.toString()
        val verdict = if (simulation.accepted) "ACCEPTED" else "REJECTED"
        model.setResult("Word '$word' is $verdict by M.")
    }

    private fun onResetClicked() {
        view.statesField.text = ""
        view.alphabetField.text = ""
        view.transitionsArea.text = ""
        view.startStateField.text = ""
        view.acceptStatesField.text = ""
        view.wordField.text = ""
        view.simulationArea.text = ""
        view.variablesArea.text = ""
        model.setDFA(null)
        model.setResult("Enter DFA components and press Build")
    }

    private fun onLoadExampleClicked() {
        when (view.exampleCombo.selectedIndex) {
            0 -> {
                view.statesField.text = "q0,q1"
                view.alphabetField.text = "a,b"
                view.transitionsArea.text = """
                    q0,a->q1
                    q0,b->q0
                    q1,a->q0
                    q1,b->q1
                """.trimIndent()
                view.startStateField.text = "q0"
                view.acceptStatesField.text = "q0"
                view.wordField.text = "baba"
            }
            1 -> {
                view.statesField.text = "S0,S1,S2"
                view.alphabetField.text = "a,b"
                view.transitionsArea.text = """
                    S0,a->S1
                    S0,b->S0
                    S1,a->S1
                    S1,b->S2
                    S2,a->S1
                    S2,b->S0
                """.trimIndent()
                view.startStateField.text = "S0"
                view.acceptStatesField.text = "S2"
                view.wordField.text = "abab"
            }
            2 -> {
                view.statesField.text = "r0,r1,r2"
                view.alphabetField.text = "0,1"
                view.transitionsArea.text = """
                    r0,0->r0
                    r0,1->r1
                    r1,0->r2
                    r1,1->r0
                    r2,0->r1
                    r2,1->r2
                """.trimIndent()
                view.startStateField.text = "r0"
                view.acceptStatesField.text = "r0"
                view.wordField.text = "1001"
            }
        }
        model.setDFA(null)
        model.setResult("Example loaded. Review fields, then click Build DFA.")
    }
}
