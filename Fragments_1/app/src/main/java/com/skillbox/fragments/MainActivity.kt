package com.skillbox.fragments

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.skillbox.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnOpenNewFragment {


    private var state = 0
    private lateinit var binding: ActivityMainBinding
    private val loginFragment = LoginFragment.newInstance()
    private val mainFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openLoginFragment()
    }

    override fun onBackPressed() {
        if (state == 1) {
            mainFragment.childFragmentManager.popBackStack("List Fragment", 0)
            state = 0
        }
        else {
            super.onBackPressed()
        }
    }

    private fun openLoginFragment() {
        supportFragmentManager.beginTransaction()
                .replace(binding.mainActivityContainer.id, loginFragment
                )
                .commit()
    }

    override fun openMainFragment() {
        supportFragmentManager.beginTransaction()
                .remove(loginFragment)
                .commit()

        supportFragmentManager.beginTransaction()
                .replace(binding.mainActivityContainer.id, mainFragment)
                .commit()
    }

    override fun openDetailFragment(text: String) {
        mainFragment.childFragmentManager.beginTransaction()
            .replace(
                findViewById<FrameLayout>(R.id.mainFragmentContainer).id,
                DetailFragment.newInstance(text)
            ).commit()
        state = 1
    }

    override fun openListFragment() {
        mainFragment.childFragmentManager.beginTransaction()
            .replace(
                findViewById<FrameLayout>(R.id.mainFragmentContainer).id,
                ListFragment.newInstance()
            ).addToBackStack("List Fragment")
            .commit()
    }
}

