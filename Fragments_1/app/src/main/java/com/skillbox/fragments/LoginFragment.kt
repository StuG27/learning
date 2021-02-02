package com.skillbox.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.skillbox.fragments.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private val openMainFragment: OnOpenNewFragment?
        get() = activity?.let { it as? OnOpenNewFragment}

    private lateinit var progress: ProgressBar
    private var validate = FormState(false, "")
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initProgressBar()

        setHeadImage()

        dataInput()

        binding.bLogin.setOnClickListener {
            login()
        }
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
        progress = ProgressBar(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun setHeadImage() {
        Glide
            .with(this)
            .load("https://i1.sndcdn.com/avatars-000494353437-cuy9dz-t500x500.jpg")
            .into(binding.ivHead)
    }

    private fun login() {
        progress.id = View.generateViewId()
        val set = ConstraintSet()
        binding.login.addView(progress)
        set.clone(binding.login)
        set.connect(progress.id, ConstraintSet.TOP, binding.tvValidate.id, ConstraintSet.BOTTOM, 24)
        set.centerHorizontally(progress.id, ConstraintSet.PARENT_ID)
        set.applyTo(binding.login)

        isViewEnable(false)
        Handler().postDelayed({
            isViewEnable(true)
            binding.login.removeView(progress)
            openMainFragment?.openMainFragment()
        }, 2000)
    }

    private fun isViewEnable (state: Boolean) {
        binding.cbAgree.isEnabled = state
        binding.etEmail.isEnabled = state
        binding.etPassword.isEnabled = state
        binding.bLogin.isEnabled = state
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
        fun newInstance(): LoginFragment {
            return LoginFragment().withArguments{
            }
        }
    }
}

