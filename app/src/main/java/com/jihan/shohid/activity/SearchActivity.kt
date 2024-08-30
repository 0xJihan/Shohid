package com.jihan.shohid.activity

import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jihan.shohid.MyApplication
import com.jihan.shohid.R
import com.jihan.shohid.adapter.MyAdapter
import com.jihan.shohid.databinding.ActivitySearchBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: ShohidViewModel
    private lateinit var adapter: MyAdapter



    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapter = MyAdapter()

        val repository = (application as MyApplication).repository
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]



        binding.searchRecyclerView.adapter = adapter

        adapter.submitList(viewModel.shoidList.value)

        viewModel.shoidList.observe(this) {
            adapter.submitList(it)
        }



        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }
        })



    }

    private fun filterList(query: String?) {
        val list = viewModel.shoidList.value?.filter { shohid ->
            val lowerCaseQuery = query?.lowercase().orEmpty()
            shohid.name.lowercase().contains(lowerCaseQuery) ||
                    shohid.en_name.lowercase().contains(lowerCaseQuery) ||
                    shohid.description.lowercase().contains(lowerCaseQuery) ||
                    shohid.en_description.lowercase().contains(lowerCaseQuery) ||
                    shohid.birth_place.lowercase().contains(lowerCaseQuery) ||
                    shohid.en_birth_place.lowercase().contains(lowerCaseQuery) ||
                    shohid.date_of_death.contains(lowerCaseQuery) ||
                    shohid.en_date_of_death.contains(lowerCaseQuery)
        }
        adapter.submitList(list)
    }
}