package com.skillbox.lists_1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.skillbox.lists_1.databinding.FragmentMainBinding
import com.skillbox.lists_1.extensions.withArguments


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.bLL.setOnClickListener {
            openLinearFragment()
        }
        binding.bGL.setOnClickListener {
            openGridFragment()
        }
        binding.bSGL.setOnClickListener {
            openStaggeredGridFragment()
        }
    }

    private fun openLinearFragment() {
        childFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(
                binding.mainFragmentContainer.id,
                LinearLayoutFragment.newInstance())
            .commit()
    }

    private fun openGridFragment() {
        childFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(
                binding.mainFragmentContainer.id,
                GridLayoutFragment.newInstance())
            .commit()
    }

    private fun openStaggeredGridFragment() {
        childFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(
                binding.mainFragmentContainer.id,
                StaggeredGridLayoutFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().withArguments {
            }
        }
    }
}