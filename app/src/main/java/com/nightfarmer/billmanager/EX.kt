package com.nightfarmer.billmanager

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by zhangfan on 2016/11/25 0025.
 */

fun EditText.onInput(callBack: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            callBack.invoke(s?.toString().orEmpty())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}
