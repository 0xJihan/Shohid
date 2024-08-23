package com.jihan.shohid.activity

import NetworkUtils
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jihan.shohid.MyApplication
import com.jihan.shohid.R
import com.jihan.shohid.adapter.MyAdapter
import com.jihan.shohid.databinding.ActivityMainBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory
import com.jihan.shohid.room.Shohid

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var viewModel: ShohidViewModel

    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = (application as MyApplication).repository

        //viewModel
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]


        // refresh ui
        binding.swipeRefreshLayout.setOnRefreshListener {

            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = true
        }


        //observing data
        viewModel.shoidList.observe(this) {
            adapter = MyAdapter(viewModel.shoidList.value!!)
            binding.recyclerView.adapter = adapter
            binding.swipeRefreshLayout.isRefreshing = false
        }


        // implementing search view
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })


        // showing toast based on network connection
        if (NetworkUtils().isInternetConnected(this)) {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_LONG).show()
        }


    }

    // filtering data based on search query
    private fun filterList(newText: String?) {
        var list = ArrayList<Shohid>()
        val shohidList = viewModel.shoidList.value!!
        val query = newText!!.lowercase()
        if (list != null) {
            for (item in shohidList) {
                if (item.name.lowercase().contains(query) || item.en_name.lowercase()
                        .contains(query) || item.description.lowercase()
                        .contains(query) || item.en_description.lowercase()
                        .contains(query) || item.birth_place.lowercase()
                        .contains(query) || item.en_birth_place.lowercase()
                        .contains(query) || item.date_of_death.contains(query) || item.en_date_of_death.contains(
                        query
                    )
                ) {
                    list.add(item)
                }
            }
        }
        adapter.updateList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()

    }


}