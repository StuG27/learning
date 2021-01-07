package com.skillbox.viewandlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isEmail = false
    var isPassword = false
    var isAgree = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                isEmail = s?.let{ it.isNotEmpty()}?: false
                b_login.isEnabled = isAgree && isEmail && isPassword
            }
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPassword = s?.let{ it.isNotEmpty()}?: false
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
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
        b_login.isEnabled = false
        cb_agree.isEnabled = false
        et_email.isEnabled = false
        et_password.isEnabled = false
        ll_main.addView(progress)
        Handler().postDelayed({
            b_login.isEnabled = true
            cb_agree.isEnabled = true
            et_email.isEnabled = true
            et_password.isEnabled = true
            ll_main.removeView(progress)
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
        }, 2000)
    }
}