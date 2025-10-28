package org.example.presentation.common.util

import javax.swing.text.AttributeSet
import javax.swing.text.BadLocationException
import javax.swing.text.DocumentFilter

/**
 * A DocumentFilter that allows only integer input (digits 0–9).
 * Prevents typing or pasting of non-numeric characters.
 *
 * Example usage:
 * (myTextField.document as AbstractDocument).documentFilter = IntegerFilter()
 */
class IntegerFilter : DocumentFilter() {

    @Throws(BadLocationException::class)
    override fun insertString(fb: FilterBypass, offset: Int, string: String?, attr: AttributeSet?) {
        if (string != null && string.all { it.isDigit() }) {
            super.insertString(fb, offset, string, attr)
        }
    }

    @Throws(BadLocationException::class)
    override fun replace(fb: FilterBypass, offset: Int, length: Int, text: String?, attrs: AttributeSet?) {
        if (text != null && text.all { it.isDigit() }) {
            super.replace(fb, offset, length, text, attrs)
        }
    }
}
