package com.skillbox.moshi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.skillbox.moshi.R
import com.skillbox.moshi.data.ViewModel
import com.skillbox.moshi.databinding.FragmentListBinding
import com.skillbox.moshi.data.RemoteMovie
import com.skillbox.moshi.network.CustomMoshiMovieAdapter
import com.squareup.moshi.Moshi


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ViewModel by viewModels()
    private var movie: RemoteMovie? = null

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
        bindViewModel()
        binding.bScore.setOnClickListener {
            showAddScore()
        }
    }

    private fun bindViewModel() {
        checkET()
        binding.bSearch.setOnClickListener {
            val queryText = binding.eTTitle.text.toString()
            viewModel.search(queryText)
        }
        viewModel.movieList.observe(viewLifecycleOwner) {
            movie = it
            updateTVIsEmpty(it)
            bind(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun showAddScore() {
        val view = layoutInflater.inflate(R.layout.add_score_dialog, null)
        AlertDialog.Builder(requireContext())
            .setTitle("Добавление оценки")
            .setView(view)
            .setPositiveButton("Да") { _, _ ->
                val eTSource: EditText = view.findViewById(R.id.eTSource)
                val eTScore: EditText = view.findViewById(R.id.eTScore)
                if (eTSource.text.isNotEmpty() && eTScore.text.isNotEmpty()) {
                    movie?.scores?.set(eTSource.text.toString(), eTScore.text.toString())
                    binding.tVScores.text = movie?.scores.toString()
                    val moshi = Moshi.Builder().add(CustomMoshiMovieAdapter()).build()
                    val adapter = moshi.adapter(RemoteMovie::class.java).nonNull()
                    try {
                        val answer = adapter.toJson(movie).toString()
                        Log.d("Answer", answer)
                    } catch (e: Exception) {
                        val answer = e.message
                        Log.d("Answer", "$answer")
                    }
                } else {
                    Toast.makeText(context, "Необходимо заполнить все поля", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("Отмена") { _, _ -> }
            .create()
            .show()
    }

    private fun checkET() {
        binding.eTTitle.doOnTextChanged { _, _, _, _ ->
            binding.bSearch.isEnabled = binding.eTTitle.text.isNotEmpty()
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.pB.isVisible = isLoading
        binding.bSearch.isEnabled = isLoading.not()
        binding.bScore.isEnabled = isLoading.not()
        binding.eTTitle.isEnabled = isLoading.not()
    }

    private fun updateTVIsEmpty(movie: RemoteMovie) {
        when (movie.year) {
            "error" -> {
                binding.tVIsEmpty.visibility = View.VISIBLE
                updateVisibility(8)
                binding.bScore.isEnabled = false
                binding.tVIsEmpty.text = "Фильм не найден или ошибка сети"
            }
            else -> {
                updateVisibility(0)
                binding.bScore.isEnabled = true
                binding.tVIsEmpty.visibility = View.GONE
            }
        }
    }

    private fun bind(movie: RemoteMovie) {
        binding.tVTitle.text = movie.title
        binding.tVRating.text = movie.rating.name
        binding.tVGenre.text = movie.genre
        binding.tVScores.text = movie.scores.toString()
        binding.tVYear.text = movie.year
        Glide
            .with(this)
            .load(movie.poster)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(binding.iVPoster)
    }

    private fun updateVisibility(flag: Int) {
        binding.tVTitle.visibility = flag
        binding.tVRating.visibility = flag
        binding.tVGenre.visibility = flag
        binding.tVScores.visibility = flag
        binding.tVYear.visibility = flag
        binding.iVPoster.visibility = flag
    }
}