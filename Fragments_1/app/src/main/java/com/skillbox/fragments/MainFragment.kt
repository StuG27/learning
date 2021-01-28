package com.skillbox.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skillbox.fragments.databinding.FragmentMainBinding

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
        childFragmentManager.popBackStack("List Fragment", 0)
        childFragmentManager.beginTransaction()
            .replace(binding.mainFragmentContainer.id, ListFragment.newInstance())
            .addToBackStack("List Fragment")
            .commit()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().withArguments{
            }
        }
    }
}