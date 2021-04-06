package com.skillbox.github.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.User
import com.skillbox.github.databinding.FragmentUserInfoBinding


class CurrentUserFragment : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: CurrentUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentUserInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.getUserInfo()
        viewModel.userInfo.observe(viewLifecycleOwner) {
            bind(it)
        }
    }

    private fun bind(user: User) {
        binding.login.text = user.login
        binding.url.text = user.url
        Glide
            .with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(binding.iV)
    }
}