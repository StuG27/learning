package com.skillbox.networking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.networking.R
import com.skillbox.networking.adapters.MovieAdapter
import com.skillbox.networking.data.MovieRepository
import com.skillbox.networking.extensions.ItemOffsetDecoration
import com.skillbox.networking.extensions.autoCleared
import com.skillbox.networking.data.ViewModel
import com.skillbox.networking.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var movieAdapter: MovieAdapter by autoCleared()
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenu()
        initList()
        bindViewModel()
    }

    private fun initMenu() {
        val type = resources.getStringArray(R.array.movie_type)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, type)
        (binding.tV as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun initList() {
        movieAdapter = MovieAdapter()

        with(binding.rV) {
            adapter = movieAdapter
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
        checkET()
        binding.bSearch.setOnClickListener {
            val queryText = binding.eTTitle.text.toString()
            val year = binding.eTYear.text.toString()
            var type: String? = binding.tV.text.toString()
            type = when (type) {
                "Сериал" -> "series"
                "Фильм" -> "movie"
                "Эпизод" -> "episode"
                else -> null
            }
            viewModel.search(queryText, year, type, 1)
        }
        viewModel.movieList.observe(viewLifecycleOwner) { movieAdapter.items = it }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun checkET() {
        binding.eTTitle.doOnTextChanged { _, _, _, _ ->
            check()
        }
        binding.eTYear.doOnTextChanged { _, _, _, _ ->
            check()
        }
        binding.tV.doOnTextChanged { _, _, _, _ ->
            check()
        }
    }

    private fun check() {
        var isEnable = false
        if (binding.tV.text.isNotEmpty() && binding.eTTitle.text.isNotEmpty()) {
            isEnable = true
            if (binding.eTYear.text.isNotEmpty()) {
                isEnable = checkYear()
            }
        }
        binding.bSearch.isEnabled = isEnable
    }

    private fun checkYear(): Boolean {
        val year = binding.eTYear.text.toString().toInt()
        return year in 1874..2030
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.rV.isVisible = isLoading.not()
        binding.pB.isVisible = isLoading
        binding.bSearch.isEnabled = isLoading.not()
        binding.eTYear.isEnabled = isLoading.not()
        binding.eTTitle.isEnabled = isLoading.not()
        binding.tV.isEnabled = isLoading.not()
        if (MovieRepository.movies.isEmpty()) {
            binding.tVIsEmpty.visibility = View.VISIBLE
        } else {
            binding.tVIsEmpty.visibility = View.GONE
        }
    }
}