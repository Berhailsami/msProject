package core.domain.model.dfa

import kotlin.text.iterator

/**
 * Deterministic Finite Automaton (DFA)
 * M = (Q, Σ, δ, q0, F)
 */
data class DFA(
    val states: Set<String>,
    val alphabet: Set<Char>,
    val transition: Map<Pair<String, Char>, String>,
    val startState: String,
    val acceptStates: Set<String>
)
{
    init {
        require(states.isNotEmpty()) { "States set cannot be empty" }
        require(startState in states) { "Start state must be in states" }
        require(acceptStates.all { it in states }) { "All accept states must be in states" }
        transition.forEach { (key, value) ->
            val (q, a) = key
            require(q in states) { "Transition uses unknown state '$q'" }
            require(a in alphabet) { "Transition uses symbol '$a' not in alphabet" }
            require(value in states) { "Transition points to unknown state '$value'" }
        }
    }

    data class Step(val from: String, val symbol: Char, val to: String)
    data class Simulation(val steps: List<Step>, val finalState: String, val accepted: Boolean)

    fun simulate(word: String): Simulation {
        val steps = mutableListOf<Step>()
        var current = startState

        if (!word.all { it in alphabet }) {
            return Simulation(steps = steps, finalState = current, accepted = false)
        }

        for (ch in word) {
            val next = transition[current to ch] ?: return Simulation(
                steps = steps,
                finalState = current,
                accepted = false
            )
            steps += Step(from = current, symbol = ch, to = next)
            current = next
        }

        val accepted = current in acceptStates
        return Simulation(steps = steps, finalState = current, accepted = accepted)
    }

    fun accepts(word: String): Boolean = simulate(word).accepted
}

object DFAParser {
    fun parse(
        statesStr: String,
        alphabetStr: String,
        transitionsStr: String,
        startStateStr: String,
        acceptStatesStr: String,
        variablesStr: String = ""
    ): DFA {
        val states = statesStr.split(',', ' ', '\n', '\t')
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

        val variables: Map<String, Set<Char>> = variablesStr.split(Regex("[\n;]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .associate { line ->
                val idx = line.indexOf(":")
                require(idx > 0 && idx < line.length - 1) { "Invalid variable declaration: '$line'. Use name: spec" }
                val name = line.substring(0, idx).trim()
                val spec = line.substring(idx + 1).trim()
                require(name.isNotEmpty()) { "Variable name cannot be empty in: '$line'" }
                val chars = parseSpecToChars(spec)
                name to chars
            }

        val explicitAlphabet = if (alphabetStr.isBlank()) emptySet() else if (alphabetStr.contains(',')) {
            alphabetStr.split(',')
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { it.single() }
                .toSet()
        } else {
            alphabetStr.trim().toSet()
        }

        val transition = mutableMapOf<Pair<String, Char>, String>()
        val transitionRules = transitionsStr.split(Regex("[\n;]+"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        transitionRules.forEach { rule ->
            val arrowIdx = rule.indexOf("->").takeIf { it >= 0 }
            val eqIdx = rule.indexOf('=')
            val parts = when {
                arrowIdx != null -> listOf(rule.substring(0, arrowIdx), rule.substring(arrowIdx + 2))
                eqIdx >= 0 -> listOf(rule.substring(0, eqIdx), rule.substring(eqIdx + 1))
                else -> throw IllegalArgumentException("Invalid transition format: '$rule'")
            }
            val left = parts[0].trim().removePrefix("(").removeSuffix(")")
            val right = parts[1].trim()
            val leftParts = left.split(',').map { it.trim() }
            require(leftParts.size == 2) { "Invalid left side in transition: '$rule'" }
            val from = leftParts[0]
            val symbolToken = leftParts[1]
            require(symbolToken.isNotEmpty()) { "Missing symbol in transition: '$rule'" }

            val symbols: Set<Char> = if (symbolToken in variables) {
                variables.getValue(symbolToken)
            } else {
                require(symbolToken.length == 1) { "Unknown variable '$symbolToken' and not a single character in transition: '$rule'" }
                setOf(symbolToken[0])
            }
            symbols.forEach { sym ->
                transition[from to sym] = right
            }
        }

        val fromVariables = variables.values.flatten().toSet()
        val fromTransitions = transition.keys.map { it.second }.toSet()
        val alphabet = (explicitAlphabet + fromVariables + fromTransitions)

        val start = startStateStr.trim()
        val accepts = acceptStatesStr.split(',', ' ', '\n', '\t')
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

        return DFA(states, alphabet, transition, start, accepts)
    }

    private fun parseSpecToChars(spec: String): Set<Char> {
        val parts = spec.split(',').map { it.trim() }.filter { it.isNotEmpty() }
        if (parts.isEmpty()) return emptySet()
        val result = mutableSetOf<Char>()
        parts.forEach { token ->
            val rangeIdx = token.indexOf('-')
            if (rangeIdx in 1 until token.length - 1) {
                val startCh = token[0]
                val endCh = token[token.length - 1]
                require(startCh <= endCh) { "Invalid range '$token' in variables" }
                for (c in startCh..endCh) result.add(c)
            } else {
                require(token.length == 1) { "Invalid literal '$token' in variables. Use single characters or ranges like a-z." }
                result.add(token[0])
            }
        }
        return result
    }
}
