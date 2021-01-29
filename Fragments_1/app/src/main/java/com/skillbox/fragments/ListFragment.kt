package com.skillbox.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.skillbox.fragments.databinding.FragmentListBinding


class ListFragment: Fragment(){

    private val openDetailFragment: OnOpenNewFragment?
        get() = parentFragment?.let { it as? OnOpenNewFragment }
            ?: activity?.let { it as? OnOpenNewFragment }

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view.let{ it as ViewGroup}
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    onButtonClick(button.text.toString())
                }
            }
    }

    private fun onButtonClick(buttonText: String){
        openDetailFragment?.openDetailFragment(buttonText)
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment().withArguments{
            }
        }
    }

}