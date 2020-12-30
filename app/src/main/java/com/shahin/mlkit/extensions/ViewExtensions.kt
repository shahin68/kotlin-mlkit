/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.mlkit.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import com.shahin.mlkit.utils.CreditCardType

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}

fun View?.visibleOrGone(visible: Boolean) {
    if (visible) {
        this?.visible()
    } else {
        this?.gone()
    }
}

fun View?.visibleOrHide(show: Boolean) {
    if (show) {
        this?.visible()
    } else {
        this?.hide()
    }
}

/**
 * Visa 	4111 1111 1111 1111
 * MasterCard 	5500 0000 0000 0004
 * American Express 	3400 0000 0000 009
 * Diner's Club 	3000 0000 0000 04
 * Carte Blanche 	3000 0000 0000 04
 * Discover 	6011 0000 0000 0004
 * en Route 	2014 0000 0000 009
 * JCB 	3088 0000 0000 0009
 */
fun AutoCompleteTextView.validateCard(block: (CreditCardType) -> Unit) {
    addTextChangedListener(
        object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                when {
                    s.isEmpty() -> {
                        block.invoke(CreditCardType.NON)
                    }
                    s.startsWith("4") -> {
                        block.invoke(CreditCardType.VISA)
                    }
                    s.startsWith("55") -> {
                        block.invoke(CreditCardType.MASTER_CARD)
                    }
                    s.startsWith("34") -> {
                        block.invoke(CreditCardType.AE)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        }
    )
}

fun AutoCompleteTextView.setDateValidator() {
    addTextChangedListener(
        object : TextWatcher {
            private var ignoreTextChange = false
            override fun afterTextChanged(s: Editable) {
                if (!ignoreTextChange && s.isNotEmpty()) {
                    if (s.length == 2) {
                        if (s.substring(0, 2).toInt() > 12) {
                            s.clear()
                        } else {
                            s.append('/')
                        }
                    }
                    if (s.length == 3 && s[2].toString() != "/") {
                        s.insert(2, "/")
                    }
                    if (s.length == 5) {
                        if (s.substring(3, 5).toInt() > 99) {
                            s.delete(3, 5)
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                ignoreTextChange = count == 0
            }
        }
    )
}

