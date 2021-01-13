package com.skillbox.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns.EMAIL_ADDRESS
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.skillbox.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var progress: ProgressBar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progress = ProgressBar(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }

        val imageView = findViewById<ImageView>(R.id.iv_head);
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(imageView)
        binding.bLogin.setOnClickListener {
            login()
        }

        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            check()
        }

        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            check()
        }

        binding.cbAgree.setOnCheckedChangeListener { _, _ ->
            check()
        }
    }

    private fun check() {
        binding.bLogin.isEnabled = binding.cbAgree.isChecked
                && binding.etPassword.text.isNotEmpty()
                && (emailValidate(binding.etEmail.text.toString()))
    }

    private fun login() {
        setFormEnable(false)
        binding.llMain.addView(progress)
        Handler().postDelayed({
            setFormEnable(true)
            binding.llMain.removeView(progress)
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
        }, 2000)
    }

    private fun emailValidate(string: String): Boolean {
        val myPattern = EMAIL_ADDRESS
        return string.matches(Regex(myPattern.toString()))
    }
    private fun setFormEnable(value: Boolean) {
        var child: View
        var i = 2
        while (i < binding.llMain.childCount) {
            child = binding.llMain.getChildAt(i)
            child.isEnabled = value
            i++
        }
    }
}