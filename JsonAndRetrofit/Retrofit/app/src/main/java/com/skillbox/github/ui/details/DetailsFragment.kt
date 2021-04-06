package com.skillbox.github.ui.details

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    private var isStarred: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        bind()
    }

    private fun bindViewModel() {
        viewModel.getStarInfo(args.owner, args.name)
        viewModel.starInfo.observe(viewLifecycleOwner) {
            when (it) {
                "204" -> {
                    isStarred = true
                    binding.tVIsStarred.text = "Звезда стоит"
                    binding.bStar.text = "Снять звезду"
                }
                "404" -> {
                    isStarred = false
                    binding.tVIsStarred.text = "Звезда не стоит"
                    binding.bStar.text = "Поставить звезду"
                }
                else -> {
                    binding.tVIsStarred.text = "Ошибка получения данных"
                    binding.bStar.visibility = View.GONE
                }
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun bind() {
        binding.tVName.text = requireContext().getString(R.string.name, args.name)
        binding.tVOwner.text = requireContext().getString(R.string.owner, args.owner)
        binding.tVUrl.text = args.url
        binding.bStar.setOnClickListener {
            if (isStarred) {
                viewModel.deleteStar(args.owner, args.name)
            } else {
                viewModel.addStar(args.owner, args.name)
            }
            val handler = Handler()
            handler.postDelayed({ viewModel.getStarInfo(args.owner, args.name) }, 200)
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.pB.isVisible = isLoading
    }

}