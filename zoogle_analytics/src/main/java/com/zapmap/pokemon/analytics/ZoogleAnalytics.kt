package com.zapmap.pokemon.analytics

import android.util.Log

object ZoogleAnalytics {

    fun logEvent(event: ZoogleAnalyticsEvent) {
        Log.i("ZoogleAnalytics", event.toString())
    }
}

data class ZoogleAnalyticsEvent(val key: String, val params: Map<String, String>)