package com.owl_laugh_at_wasted_time.mostpopularfilms.di

import android.content.Context

object Initializer {
    fun component(context: Context): AppComponent {
        return DaggerAppComponent.factory().create(context)
    }
}