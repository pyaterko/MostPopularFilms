package com.owl_laugh_at_wasted_time.mostpopularfilms.di

import android.content.Context
import com.owl_laugh_at_wasted_time.mostpopularfilms.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(

)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun context(): Context
    fun inject(activity: MainActivity)

}