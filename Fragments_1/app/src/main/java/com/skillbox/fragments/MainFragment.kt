package com.skillbox.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.skillbox.fragments.databinding.FragmentMainBinding

class MainFragment : Fragment(), OnOpenNewChildFragment {

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
        openListFragment()
    }

    private fun openListFragment() {
        val screenLayout = resources.configuration.screenLayout
        if (screenLayout and Configuration.SCREENLAYOUT_LAYOUTDIR_MASK
            == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            childFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(
                    binding.mainFragmentContainer.id,
                    ListFragment.newInstance()
                ).addToBackStack("List Fragment")
                .commit()
        }
    }

    override fun openDetailFragment(text: String) {
        childFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(
                binding.mainFragmentContainer.id,
                DetailFragment.newInstance(text)
            ).addToBackStack("Detail Fragment")
            .commit()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().withArguments{
            }
        }
    }
}