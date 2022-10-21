package com.owl_laugh_at_wasted_time.mostpopularfilms.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owl_laugh_at_wasted_time.mostpopularfilms.R
import com.owl_laugh_at_wasted_time.mostpopularfilms.di.Initializer

class MainActivity : AppCompatActivity() {

    val component by lazy {
        Initializer.component(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}