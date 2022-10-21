package com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state


sealed class AppState<out T> : IAppState {
    data class Success<out T>(val data: T) : AppState<T>()
    data class Error(val error: Throwable) : AppState<Nothing>()
    object Loading : AppState<Nothing>()
}

