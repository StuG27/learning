package com.skillbox.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skillbox.fragments.databinding.FragmentDetailBinding

class DetailFragment : Fragment(){



    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.textView.text = requireArguments().getString(KEY_TEXT)
    }


    companion object {

        private const val KEY_TEXT = "key_text"
        fun newInstance(text:String): DetailFragment {
            return DetailFragment().withArguments{
                putString(KEY_TEXT, text)
            }
        }
    }
}