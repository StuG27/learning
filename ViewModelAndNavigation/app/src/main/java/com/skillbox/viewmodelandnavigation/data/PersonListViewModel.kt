package com.skillbox.viewmodelandnavigation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.viewmodelandnavigation.extensions.SingleLiveEvent

class PersonListViewModel: ViewModel() {

    private val repository = PersonRepository()

    private val personLiveData = MutableLiveData(repository.persons)

    val persons: LiveData<ArrayList<Person>>
        get() = personLiveData

    private val showToastLiveData = SingleLiveEvent<Unit>()

    val showToast: LiveData<Unit>
        get() = showToastLiveData

    fun addPerson() {
        val newPerson = repository.createPerson()
        val updatedList = (listOf(newPerson) + personLiveData.value!!) as ArrayList<Person>
        personLiveData.postValue(updatedList)
        showToastLiveData.postValue(Unit)
    }

    fun deletePerson(position: Int) {
        personLiveData.postValue(
            repository.deletePerson(personLiveData.value!!, position) as ArrayList<Person>
        )
    }
}