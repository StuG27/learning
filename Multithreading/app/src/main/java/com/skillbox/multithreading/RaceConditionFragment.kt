package com.skillbox.multithreading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.skillbox.multithreading.databinding.FragmentRaceConditionBinding

class RaceConditionFragment : Fragment() {

    private lateinit var binding: FragmentRaceConditionBinding
    private var value = 0L
    private var threadCount = 0L
    private var increment = 0L
    private var expectedValue = 0L
    private var isSynchronized = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRaceConditionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        increment()
    }

    private fun increment() {
        checkET()
        binding.bNotSynchronized.setOnClickListener {
            isSynchronized = false
            calculate()
        }
        binding.bSynchronized.setOnClickListener {
            isSynchronized = true
            calculate()
        }
    }

    private fun calculate() {
        threadCount = binding.eTNumberOfThreads.text.toString().toLong()
        increment = binding.eTIncrement.text.toString().toLong()
        expectedValue = value + threadCount * increment * increment
        val startTime = System.currentTimeMillis()
        (0 until threadCount).map {
            Thread {
                if (isSynchronized) {
                    synchronized(this) {
                        for (i in 0 until increment) {
                            value += increment
                        }
                    }
                } else {
                    for (i in 0 until increment) {
                        value += increment
                    }
                }
            }.apply {
                start()
            }
        }
            .map { it.join() }
        val incrementTime = System.currentTimeMillis() - startTime
        binding.TvResult.text = requireContext().getString(
            R.string.result,
            expectedValue,
            value,
            incrementTime
        )
        expectedValue = 0
        value = 0
    }

    private fun checkET() {
        binding.eTNumberOfThreads.doOnTextChanged { _, _, _, _ ->
            check()
        }
        binding.eTIncrement.doOnTextChanged { _, _, _, _ ->
            check()
        }
    }

    private fun check() {
        val isEnable = binding.eTIncrement.text.isNotEmpty()
                && binding.eTNumberOfThreads.text.isNotEmpty()
        binding.bNotSynchronized.isEnabled = isEnable
        binding.bSynchronized.isEnabled = isEnable
    }
}