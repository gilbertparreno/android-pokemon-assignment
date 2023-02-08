package com.zapmap.pokemon.core.extensions

import android.os.SystemClock
import android.view.View

inline fun View.setDebounceClickListener(
    debounceTime: Long = 600L,
    crossinline action: () -> Unit
) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action.invoke()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}