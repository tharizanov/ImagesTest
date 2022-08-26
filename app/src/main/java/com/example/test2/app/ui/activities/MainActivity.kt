package com.example.test2.app.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test2.app.R
import com.example.test2.app.events.NavigateToDetailsEvent
import com.example.test2.app.events.NavigationEvent
import com.example.test2.app.ui.fragments.details.DetailsFragment
import org.koin.androidx.fragment.android.replace
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NavigateToDetailsEvent -> navigateToDetailsFragment(event.link)
        }
    }

    private fun navigateToDetailsFragment(link: String) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace<DetailsFragment>(
                containerViewId = R.id.main_fragment_container,
                args = Bundle().apply { putString(DetailsFragment.ARGS_KEY_LINK, link) },
                tag = null
            )
            .commit()
    }

}