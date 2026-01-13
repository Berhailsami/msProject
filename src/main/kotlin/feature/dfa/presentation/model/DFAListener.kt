package feature.dfa.presentation.model

import core.domain.model.dfa.DFA

interface DFAListener {
    fun onDFABuilt(dfa: DFA?)
    fun onResultChanged(resultText: String)
}
