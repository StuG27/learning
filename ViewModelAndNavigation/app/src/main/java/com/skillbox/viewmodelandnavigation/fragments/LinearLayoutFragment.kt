package com.skillbox.viewmodelandnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.viewmodelandnavigation.ItemOffsetDecoration
import com.skillbox.viewmodelandnavigation.adapters.PersonAdapterShort
import com.skillbox.viewmodelandnavigation.autoCleared
import com.skillbox.myapplication.databinding.FragmentLinearLayoutBinding
import com.skillbox.viewmodelandnavigation.data.PersonListViewModel
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator


class LinearLayoutFragment : Fragment() {

    private lateinit var binding: FragmentLinearLayoutBinding
    private var personAdapter: PersonAdapterShort by autoCleared()
    private val personListViewModel: PersonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLinearLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        binding.fABAdd.setOnClickListener {
            addPerson()
        }
        observeViewModelState()
    }

    private fun initList() {
        personAdapter = PersonAdapterShort({
                id  -> val actions = LinearLayoutFragmentDirections
            .actionLinearLayoutFragmentToDetailsFragment(id)
            findNavController().navigate(actions)},
            {  position -> deletePerson(position)  }
        )


        with(binding.rV) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(context).apply {
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


    private fun deletePerson(position: Int) {
        personListViewModel.deletePerson(position)
    }

    private fun addPerson() {
        personListViewModel.addPerson()
        binding.rV.scrollToPosition(0) //Не работает
    }

    private fun observeViewModelState() {
        personListViewModel.persons
            .observe(viewLifecycleOwner) { newPersons -> personAdapter.items = newPersons }
        personListViewModel.showToast
            .observe(viewLifecycleOwner) {
                Toast.makeText(context, "Добавили персону", Toast.LENGTH_SHORT).show()
            }
    }
}