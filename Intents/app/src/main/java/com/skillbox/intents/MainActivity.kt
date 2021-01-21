package com.skillbox.intents

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.skillbox.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()

        setHeadImage()

        handleIntentData()

        binding.bCall.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            if (callIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(callIntent, IS_CALL)
            }
        }

        binding.etPhoneNumber.doOnTextChanged { _, _, _, _ ->
            phoneNumberValidate()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IS_CALL){
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, R.string.result_ok, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.result_no, Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun phoneNumberValidate() {
        val myPattern = Patterns.PHONE
        binding.bCall.isEnabled = binding.etPhoneNumber.text.toString()
            .matches(Regex(myPattern.toString()))
    }

    private fun setHeadImage(){
        val imageView = findViewById<ImageView>(R.id.iv_head);
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(imageView)
    }

    private fun handleIntentData() {
        intent.data?.path?.let {
            binding.tvUrl.text = it
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.path?.let {
            binding.tvUrl.text = it
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
        private const val IS_CALL = 100
    }
}
