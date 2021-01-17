package com.skillbox.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.skillbox.activitylifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var progress: ProgressBar
    private lateinit var binding: ActivityMainBinding
    private val tag = "MainActivity"
    private var validate = FormState(false,"")

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

        binding.ANRButton.setOnClickListener {
            Thread.sleep(10000)
        }

        binding.bLogin.setOnClickListener {
            checkValidate()
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
        Log.println(Log.VERBOSE, tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.println(Log.DEBUG, tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.println(Log.INFO, tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.println(Log.WARN, tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.println(Log.ERROR, tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.println(Log.ASSERT, tag, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(FORM_STATE, validate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        validate = savedInstanceState.getParcelable(FORM_STATE)
                ?: FormState(false,"")
        updateValidate(validate.valid, validate.message)
    }

    private fun check() {
        binding.bLogin.isEnabled = binding.cbAgree.isChecked
                && binding.etPassword.text.isNotEmpty()
                && binding.etEmail.text.isNotEmpty()
    }

    private fun checkValidate(){
        when {
            !emailValidate(binding.etEmail.text.toString()) ->
                updateValidate(false, getString(R.string.wrong_email))
            !passwordValidate(binding.etPassword.text.toString()) ->
                updateValidate(false, getString(R.string.wrong_password))
            else -> {
                updateValidate(true, "")
                login()
            }
        }
    }

    private fun login() {
        progress.id = View.generateViewId()
        val set = ConstraintSet()
        binding.ClMain.addView(progress)
        set.clone(binding.ClMain)
        set.connect(progress.id, ConstraintSet.TOP, R.id.ANRButton, ConstraintSet.BOTTOM,24)
        set.centerHorizontally(progress.id, ConstraintSet.PARENT_ID)
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

    private fun passwordValidate(string: String): Boolean {
        val myPattern = "(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$"
        return string.matches(Regex(myPattern))
    }

    private fun updateValidate(valid: Boolean, message: String){
        validate.valid = valid
        validate.message = message
        if (valid) binding.tvIsValidate.text = getString(R.string.is_validate)
        else binding.tvIsValidate.text = ""
        binding.tvValidate.text = validate.message
    }

    companion object{
        private const val FORM_STATE = "formState"
    }
}

