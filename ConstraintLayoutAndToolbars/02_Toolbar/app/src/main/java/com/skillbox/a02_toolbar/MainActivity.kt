package com.skillbox.a02_toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.skillbox.a02_toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val users = listOf(
        "Man1",
        "Man2",
        "Man3",
        "Woman1",
        "Woman2",
        "Woman3",
        "Animal1",
        "Animal2",
        "Animal3"
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
    }

    private fun toast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initToolbar(){
        binding.toolbar.title = "Toolbar"
        binding.toolbar.setNavigationOnClickListener {
            toast("Navigation clicked")
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.attachment ->{
                    toast("Attachment")
                    true
                }
                R.id.action ->{
                    toast("action_2")
                    true
                }
                else -> false
            }
        }

        val searchItem = binding.toolbar.menu.findItem(R.id.search)

        (searchItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter{it.contains(newText ?: "", true)}
                    .joinToString().let{
                        binding.searchResultTextView.text = it
                }
                return true
            }

        })
    }


}