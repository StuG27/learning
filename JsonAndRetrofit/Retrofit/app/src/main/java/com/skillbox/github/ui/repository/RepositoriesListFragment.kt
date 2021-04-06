package com.skillbox.github.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.data.adapters.RepositoryAdapter
import com.skillbox.github.databinding.FragmentRepositoriesListBinding
import com.skillbox.github.extensions.ItemOffsetDecoration
import com.skillbox.github.extensions.autoCleared
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator


class RepositoriesListFragment : Fragment() {

    private lateinit var binding: FragmentRepositoriesListBinding
    private var repositoryAdapter: RepositoryAdapter by autoCleared()
    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRepositoriesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        repositoryAdapter = RepositoryAdapter { name, owner, url ->
            val actions = RepositoriesListFragmentDirections
                .actionRepositoryListFragmentToDetailsFragment(name, owner, url)
            findNavController().navigate(actions)
        }
        with(binding.rV) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
            setHasFixedSize(true)
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            itemAnimator = SlideInRightAnimator()
        }
    }

    private fun bindViewModel() {
        viewModel.getRepositoriesList()
        viewModel.repositoryInfo.observe(viewLifecycleOwner) {
            repositoryAdapter.items = it
        }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.rV.isVisible = isLoading.not()
        binding.pB.isVisible = isLoading
    }
}