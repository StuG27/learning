package com.skillbox.a01_constraintlayout

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import com.skillbox.a01_constraintlayout.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var progress: ProgressBar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progress = ProgressBar(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val imageView = findViewById<ImageView>(R.id.iv_head);
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(imageView)
        binding.bLogin.setOnClickListener {
            login()
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                check()
            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                check()
            }
        })

        binding.cbAgree.setOnCheckedChangeListener { _, _ ->
            check()
        }
    }

    private fun check() {
        binding.bLogin.isEnabled = binding.cbAgree.isChecked
                && binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()
                && (emailValidate(binding.etEmail.text.toString()))
    }

    private fun login() {
        progress.id = View.generateViewId()
        val set = ConstraintSet()
        binding.ClMain.addView(progress)
        set.clone(binding.ClMain)
        set.connect(progress.id, ConstraintSet.TOP, R.id.guideline4, ConstraintSet.BOTTOM,0)
        set.centerHorizontally(progress.id,ConstraintSet.PARENT_ID)
        set.applyTo(binding.ClMain)

        binding.group.referencedIds.forEach {
            findViewById<View>(it).isEnabled = false
        }
        Handler().postDelayed({
            binding.group.referencedIds.forEach {
                findViewById<View>(it).isEnabled = true
            }
            binding.ClMain.removeView(progress)
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
        }, 2000)
    }

    private fun emailValidate(string: String): Boolean {
        val myPattern = Patterns.EMAIL_ADDRESS
        return string.matches(Regex(myPattern.toString()))
    }
}

