package com.owl_laugh_at_wasted_time.mostpopularfilms.di

import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.details.DetailViewModel
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.fragments.list.ListFilmsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListFilmsViewModel::class)
    fun bindListFilmsViewModel(viewModel: ListFilmsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}
