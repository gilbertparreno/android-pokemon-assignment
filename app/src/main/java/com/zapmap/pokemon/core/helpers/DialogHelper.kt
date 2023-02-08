package com.zapmap.pokemon.core.helpers

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner

object DialogHelper {

    inline fun showDialog(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        @StringRes dialogTitleRes: Int,
        @StringRes dialogMessageRes: Int,
        @StringRes positiveButtonTextRes: Int,
        @StringRes negativeButtonTextRes: Int,
        crossinline onPositive: (() -> Unit) = {},
        crossinline onNegative: (() -> Unit) = {},
        isCancellable: Boolean = true
    ) {
        MaterialDialog(context).show {
            title(res = dialogTitleRes)
            message(res = dialogMessageRes)
            positiveButton(res = positiveButtonTextRes) {
                onPositive.invoke()
            }
            negativeButton(res = negativeButtonTextRes) {
                onNegative.invoke()
            }
            cancelable(isCancellable)
            lifecycleOwner(lifecycleOwner)
        }
    }
}