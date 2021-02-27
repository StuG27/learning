package com.skillbox.permissionsanddate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.skillbox.permissionsanddate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val locationFragment = LocationFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openLocationFragment()
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            PERMISSION_REQUEST_CODE
//        )
    }

    private fun openLocationFragment() {
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(binding.appActivityContainer.id, locationFragment)
                .commit()
    }

//    companion object {
//        private const val PERMISSION_REQUEST_CODE = 1
//    }
}