package com.skillbox.moshi.ui

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
import com.skillbox.moshi.R
import com.skillbox.moshi.adapters.MovieAdapter
import com.skillbox.moshi.data.MovieRepository
import com.skillbox.moshi.data.ViewModel
import com.skillbox.moshi.databinding.FragmentListBinding
import com.skillbox.moshi.extensions.ItemOffsetDecoration
import com.skillbox.moshi.extensions.autoCleared
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
//            val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
//                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
//                    loadNextDataFromApi(page)
//                }
//            }
//            binding.rV.addOnScrollListener(scrollListener)
        }
    }

//    private fun loadNextDataFromApi(page: Int) {
//        val queryText = binding.eTTitle.text.toString()
//        viewModel.search(queryText, page+1)
//        viewModel.movieList.observe(viewLifecycleOwner) { movieAdapter.items = it }
//        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
//        Toast.makeText(context, "Загружаю страницу $page", Toast.LENGTH_SHORT).show()
//    }

    private fun bindViewModel() {
        checkET()
        binding.bSearch.setOnClickListener {
            val queryText = binding.eTTitle.text.toString()
            val age = binding.eTTitle.text.toString()
            var type = binding.tV.text.toString()
            when (type) {
                "Сериал" -> type = "series"
                "Фильм" -> type = "movie"
                "Эпизод" -> type = "episode"
            }
            viewModel.search(queryText, age, type, 1)
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
        val isEnable = binding.tV.text.isNotEmpty()
                && binding.eTTitle.text.isNotEmpty() || checkAge()
        binding.bSearch.isEnabled = isEnable
    }

    private fun checkAge(): Boolean {
        return if (binding.eTYear.text.isNotEmpty()) {
            val year = binding.eTYear.text.toString().toInt()
            year in 1874..2030
        } else {
            false
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.rV.isVisible = isLoading.not()
        binding.pB.isVisible = isLoading
        binding.bSearch.isEnabled = isLoading.not()
        if (MovieRepository.movies.isEmpty()) {
            binding.tVIsEmpty.visibility = View.VISIBLE
        } else {
            binding.tVIsEmpty.visibility = View.GONE
        }
    }
}