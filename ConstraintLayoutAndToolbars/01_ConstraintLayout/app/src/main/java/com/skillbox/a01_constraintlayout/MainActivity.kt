package com.skillbox.a01_constraintlayout

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isEmail = false
        var isPassword = false
        var isAgree = false

        val imageView = findViewById<ImageView>(R.id.iv_head);
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(imageView)
        b_login.setOnClickListener {
            login()
        }

        et_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isEmail = s?.let { it.isNotEmpty() } ?: false
                b_login.isEnabled = isAgree && isEmail && isPassword
            }
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPassword = s?.let { it.isNotEmpty() } ?: false
                b_login.isEnabled = isAgree && isEmail && isPassword
            }
        })

        cb_agree.setOnCheckedChangeListener { _, isChecked ->
            isAgree = isChecked
            b_login.isEnabled = isChecked && isEmail && isPassword
        }


    }

    private fun login() {
        val progress = ProgressBar(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
        progress.id = View.generateViewId()
        val set = ConstraintSet()
        Cl_main.addView(progress)
        set.clone(Cl_main)
        set.connect(progress.id, ConstraintSet.TOP, R.id.guideline4, ConstraintSet.BOTTOM,0)
        set.centerHorizontally(progress.id,ConstraintSet.PARENT_ID)
        set.applyTo(Cl_main)
        group.referencedIds.forEach {
            findViewById<View>(it).isEnabled = false
        }
        Handler().postDelayed({
            group.referencedIds.forEach {
                findViewById<View>(it).isEnabled = true
            }
            Cl_main.removeView(progress)
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
        }, 2000)
    }
}

