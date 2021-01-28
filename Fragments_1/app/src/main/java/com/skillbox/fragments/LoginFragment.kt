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

//        validate = savedInstanceState?.getParcelable(FORM_STATE)
//            ?: FormState(false, "")
//        updateValidate(validate.valid, validate.message)

        initProgressBar()

        setHeadImage()

        dataInput()

        binding.bLogin.setOnClickListener {
            login()
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(FORM_STATE, validate)
//    }


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


        binding.cbAgree.isEnabled = false
        binding.etEmail.isEnabled = false
        binding.etPassword.isEnabled = false
        binding.bLogin.isEnabled = false

//        binding.group.referencedIds.forEach {
//            findViewById<View>(it).isEnabled = false
//        }
        Handler().postDelayed({
            binding.cbAgree.isEnabled = true
            binding.etEmail.isEnabled = true
            binding.etPassword.isEnabled = true
            binding.bLogin.isEnabled = true
//            binding.group.referencedIds.forEach {
//                findViewById<View>(it).isEnabled = true
//            }
            binding.login.removeView(progress)
            openMainFragment?.openMainFragment()
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
//        private const val FORM_STATE = "formState"

        fun newInstance(): LoginFragment {
            return LoginFragment().withArguments{
            }
        }
    }
}

