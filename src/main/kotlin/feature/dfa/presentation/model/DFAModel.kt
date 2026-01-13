package feature.dfa.presentation.model

import core.domain.model.dfa.DFA
import javax.swing.SwingUtilities

class DFAModel {
    private val listeners = mutableListOf<DFAListener>()

    var dfa: DFA? = null
        private set

    var lastResult: String = ""
        private set

    fun addListener(listener: DFAListener) {
        listeners.add(listener)
    }

    fun setDFA(newDFA: DFA?) {
        dfa = newDFA
        SwingUtilities.invokeLater {
            listeners.forEach { it.onDFABuilt(dfa) }
        }
    }

    fun setResult(text: String) {
        lastResult = text
        SwingUtilities.invokeLater {
            listeners.forEach { it.onResultChanged(text) }
        }
    }
}
