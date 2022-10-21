package com.owl_laugh_at_wasted_time.mostpopularfilms.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.AppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.domain.state.IAppState
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.MainActivity
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

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