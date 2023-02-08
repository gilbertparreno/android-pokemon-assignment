package com.zapmap.pokemon.core.extensions

import android.os.Handler
import android.os.Looper
import timber.log.Timber

fun Any.logDebug(message: String) {
    Timber.d("${javaClass.simpleName}: $message")
}

fun Any.logInfo(message: String) {
    Timber.i("${javaClass.simpleName}: $message")
}

fun Any.logError(message: String) {
    Timber.e("${javaClass.simpleName}: $message")
}

fun Any.logError(throwable: Throwable) {
    Timber.e("${javaClass.simpleName}: ${throwable.message}")
}

fun allTrue(vararg booleans: Boolean): Boolean {
    return booleans.all { it }
}

fun Any.anyTrue(vararg booleans: Boolean): Boolean {
    return booleans.any { it }
}

fun runDelayed(
    delayMillis: Long,
    action: () -> Unit
) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(action), delayMillis)
}