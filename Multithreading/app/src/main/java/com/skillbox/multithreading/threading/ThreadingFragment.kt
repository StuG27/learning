package com.skillbox.multithreading.threading

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.multithreading.adapters.MovieAdapter
import com.skillbox.multithreading.data.ViewModel
import com.skillbox.multithreading.databinding.FragmentThreadingBinding
import com.skillbox.multithreading.extensions.ItemOffsetDecoration
import com.skillbox.multithreading.extensions.autoCleared
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator

class ThreadingFragment : Fragment() {

    private lateinit var binding: FragmentThreadingBinding
    private var movieAdapter: MovieAdapter by autoCleared()
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentThreadingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initRefresh()
        bindViewModel()
        searchMovies()
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
            addItemDecoration(
                ItemOffsetDecoration(
                    requireContext(),
                    10,
                    10,
                    10,
                    10
                )
            )
            itemAnimator = SlideInRightAnimator()
        }
    }



    private fun bindViewModel() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieAdapter.items = it }
    }

    private fun searchMovies() {
        viewModel.search()
        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    Toast.makeText(requireContext(), "Список обновлён", Toast.LENGTH_SHORT)
                        .show()
                    binding.swipeRefresh.isRefreshing = false
                },
                1000
            )
    }

    private fun initRefresh() {
        binding.swipeRefresh.setColorSchemeColors(Color.BLUE)
        binding.swipeRefresh.setOnRefreshListener {
            searchMovies()
        }
    }
}