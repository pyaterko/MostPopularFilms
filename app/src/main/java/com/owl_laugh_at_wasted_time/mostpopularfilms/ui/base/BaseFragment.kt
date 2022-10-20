package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.IAppState

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    abstract fun initListeners()
    abstract fun initObservers()

    protected fun renderData(result: IAppState) {
        when (result) {
            is AppState.Error -> {
                showLoading(false)
                showError(result.error)
            }
            is AppState.Loading -> showLoading(true)
            is AppState.Success<*> -> renderSuccess(result)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    abstract fun renderSuccess(result: AppState.Success<*>)
    abstract fun showLoading(isShow: Boolean)
    abstract fun showError(throwable: Throwable)


}