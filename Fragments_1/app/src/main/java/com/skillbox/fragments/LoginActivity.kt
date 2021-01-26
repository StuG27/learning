package com.skillbox.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.skillbox.fragments.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var progress: ProgressBar
    private lateinit var binding: ActivityLoginBinding
    private var validate = FormState(false, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProgressBar()

        setHeadImage()

        dataInput()

        binding.bLogin.setOnClickListener {
            login()
            startActivity(MainActivity.getIntent(this))
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(FORM_STATE, validate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        validate = savedInstanceState.getParcelable(FORM_STATE)
            ?: FormState(false, "")
        updateValidate(validate.valid, validate.message)
    }



    private fun checkValidate() {
        binding.bLogin.isEnabled = false
        when {
            !emailValidate(binding.etEmail.text.toString()) ->
                updateValidate(false, getString(R.string.wrong_email))
            !passwordValidate(binding.etPassword.text.toString()) ->
                updateValidate(false, getString(R.string.wrong_password))
            !binding.cbAgree.isChecked ->
                updateValidate(false, getString(R.string.agree))
            else -> {
                updateValidate(true, "")
                binding.bLogin.isEnabled = true
            }
        }
    }

    private fun dataInput() {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            checkValidate()
        }

        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            checkValidate()
        }

        binding.cbAgree.setOnCheckedChangeListener { _, _ ->
            checkValidate()
        }
    }

    private fun initProgressBar() {
        progress = ProgressBar(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun setHeadImage() {
        val imageView = findViewById<ImageView>(R.id.iv_head);
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(imageView)
    }

    private fun login() {
        progress.id = View.generateViewId()
        val set = ConstraintSet()
        binding.ClLogin.addView(progress)
        set.clone(binding.ClLogin)
        set.connect(progress.id, ConstraintSet.TOP, R.id.tv_validate, ConstraintSet.BOTTOM, 24)
        set.centerHorizontally(progress.id, ConstraintSet.PARENT_ID)
        set.applyTo(binding.ClLogin)

        binding.group.referencedIds.forEach {
            findViewById<View>(it).isEnabled = false
        }
        Handler().postDelayed({
            binding.group.referencedIds.forEach {
                findViewById<View>(it).isEnabled = true
            }
            binding.ClLogin.removeView(progress)
        }, 2000)
    }

    private fun emailValidate(string: String): Boolean {
        val myPattern = Patterns.EMAIL_ADDRESS
        return string.matches(Regex(myPattern.toString()))
    }

    private fun passwordValidate(string: String): Boolean {
        val myPattern = "(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$"
        return string.matches(Regex(myPattern))
    }

    private fun updateValidate(valid: Boolean, message: String) {
        validate.valid = valid
        validate.message = message
        binding.tvValidate.text = validate.message
    }

    companion object {
        private const val FORM_STATE = "formState"
    }
}

