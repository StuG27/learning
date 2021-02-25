package com.skillbox.lists_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.skillbox.lists_1.databinding.ActivityAppBinding
import com.skillbox.lists_1.fragments.MainFragment

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding
    private val listFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openListFragment()
        }
    }

    private fun openListFragment() {
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(binding.appActivityContainer.id, listFragment)
                .commit()
    }
}