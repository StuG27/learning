package com.skillbox.lists_1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.lists_1.R
import com.skillbox.lists_1.adapters.PersonAdapter
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.databinding.FragmentListBinding
import com.skillbox.lists_1.extensions.withArguments



class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var personAdapter: PersonAdapter? = null
    private var persons = arrayListOf(
        Person.Actor(
            "Том Хэнкс",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3acd328c-721a-47ac-a7bf-fe7d5efb69fc/280x420",
            64,
            true
        ),
        Person.Producer(
            "Питер Джексон",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/489b2e6a-555c-40c2-82c3-942adee761e8/280x420",
            59,
            true,
            "Властелин колец: Возвращение Короля"
        ),
        Person.Actor(
            "Джонни Депп",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/bcaecab2-5007-40d4-82e5-38cb54f24454/280x420",
            57,
            false
        ),
        Person.Actor(
            "Том Круз",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/2eb2fc4d-a8bd-43b0-83cd-35feacb8ccae/280x420",
            58,
            false
        ),
        Person.Producer(
            "Гильермо дель Торо",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/66eab548-dafa-4119-8f04-0a59d08ff4e5/280x420",
            56,
            true,
            "Лабиринт Фавна"
        ),
        Person.Actor(
            "Шэрон Стоун",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/8cb776b6-1ba1-4307-a003-3f4706eb1b80/280x420",
            62,
            false
        ),
        Person.Actor(
            "Хоакин Феникс",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/85880973-3516-4ad5-91ae-c9ead4d10f77/280x420",
            46,
            true
        ),
        Person.Producer(
            "Кристофер Нолан",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/2aeee5fd-2f79-4897-a1d2-eec29f666e35/280x420",
            50,
            false,
            "Начало"
        ),
        Person.Producer(
            "Квентин Тарантино",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/c5eef897-dfb2-42a3-bc17-d5346f5dc587/280x420",
            57,
            false,
            "Криминальное чтиво"
        ),
        Person.Actor(
            "Леонардо ДиКаприо",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/9c33caef-be28-4257-b7ed-a407698f1a32/280x420",
            46,
            true
        ),
        Person.Actor(
            "Антонио Бандерас",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/52baaa66-5da3-41cc-b0a0-a772716b8804/280x420",
            60,
            false
        ),
        Person.Producer(
            "Ридли Скотт",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3dc522eb-e149-4ff3-ab4c-45d9e4ebda05/280x420",
            83,
            false,
            "Гладиатор"
        ),
        Person.Actor(
            "Анджелина Джоли",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/d91cf99a-63a2-432a-a72b-ae0b8c6ea1d6/280x420",
            45,
            true
        ),
        Person.Actor(
            "Ева Грин",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/8e817261-9521-426e-a51f-57a8ce6a92d3/280x420",
            40,
            false
        )
    )

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
        initList()
        binding.fABAdd.setOnClickListener {
            showAddPersonLayout()
        }
        personAdapter?.update(persons)
        personAdapter?.notifyDataSetChanged()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putCharSequenceArrayList(PERSONS, persons)
//    }

    private fun initList() {
        personAdapter = PersonAdapter { position -> deletePerson(position) }
        with(binding.rV) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
//                .apply {
//                orientation = RecyclerView.HORIZONTAL
//            layoutManager = GridLayoutManager(context, 2).apply {
//                orientation = RecyclerView.HORIZONTAL
//                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
//                    override fun getSpanSize(position: Int): Int {
//                        return  if (position % 3 == 0) 2 else 1
//                    }
//                }
//            }
            setHasFixedSize(true)
//            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
//            addItemDecoration(dividerItemDecoration)
//            addItemDecoration(ItemOffsetDecoration(requireContext()))
//            itemAnimator = SlideInRightAnimator()
        }
    }

//    private fun addActor() {
//        val newActor = persons.random()
//        persons = listOf(newActor) + persons
//        personAdapter?.update(persons)
//        personAdapter?.notifyItemInserted(0)
//        binding.rV.scrollToPosition(0)
//    }

    private fun deletePerson(position: Int) {
        persons = persons.filterIndexed{ index, _ -> index != position } as ArrayList<Person>
        personAdapter?.update(persons)
        personAdapter?.notifyItemRemoved(position)
        if (persons.isEmpty()) {
            binding.tV.visibility = View.VISIBLE
        }
    }

    private fun showAddPersonLayout() {
        val dialog = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.add_actor_dialog, null)
        dialog.setTitle("Добавление нового персонажа")
            .setView(view)
            .setPositiveButton("Да") { _, _ ->
                val eTName: EditText = view.findViewById(R.id.eTName)
                Toast.makeText(context, eTName.text.toString(), Toast.LENGTH_SHORT).show()
                val eTAvatarLink: EditText = view.findViewById(R.id.eTAvatarLink)
                val eTAge: EditText = view.findViewById(R.id.eTAge)
                val cBOscar: CheckBox = view.findViewById(R.id.cBOscar)
                val eTBestFilm: EditText = view.findViewById(R.id.eTBestFilm)
                if (eTBestFilm.text.isNotEmpty()) {
                    val newPerson = Person.Producer(
                        eTName.text.toString(),
                        eTAvatarLink.text.toString(),
                        eTAge.text.toString().toInt(),
                        cBOscar.isChecked,
                        eTBestFilm.text.toString()
                    )
                    persons = (listOf(newPerson) + persons) as ArrayList<Person>
                } else {
                    val newPerson = Person.Actor(
                        eTName.text.toString(),
                        eTAvatarLink.text.toString(),
                        eTAge.text.toString().toInt(),
                        cBOscar.isChecked
                    )
                    persons = (listOf(newPerson) + persons) as ArrayList<Person>
                }
                personAdapter?.update(persons)
                personAdapter?.notifyItemInserted(0)
                binding.rV.scrollToPosition(0)
            }
            .setNegativeButton("Отмена") { _, _ -> }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
// AutoClearedValue
    }

    companion object {
        const val PERSONS = "persons"
        fun newInstance(): ListFragment {
            return ListFragment().withArguments {
            }
        }
    }
}