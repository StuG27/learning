package com.skillbox.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.skillbox.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnOpenNewFragment {

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
        if ((mainFragment.isAdded) && (mainFragment.childFragmentManager.backStackEntryCount > 1)){
            mainFragment.childFragmentManager.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    private fun openLoginFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(binding.mainActivityContainer.id, loginFragment)
            .commit()
    }

    override fun openMainFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(binding.mainActivityContainer.id, mainFragment)
            .commit()
    }
}

