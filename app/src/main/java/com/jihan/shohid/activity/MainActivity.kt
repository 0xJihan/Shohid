package com.jihan.shohid.activity

import NetworkUtils
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jihan.shohid.MyApplication
import com.jihan.shohid.R
import com.jihan.shohid.adapter.MyAdapter
import com.jihan.shohid.databinding.ActivityMainBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var viewModel: ShohidViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = (application as MyApplication).repository

        //viewModel
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]



        binding.swipeRefreshLayout.setOnRefreshListener {

            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = true
        }


        //observing data
        viewModel.shoidList.observe(this) {
            binding.recyclerView.adapter = MyAdapter(viewModel.shoidList.value!!)
            binding.swipeRefreshLayout.isRefreshing = false
        }



        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)





        if (NetworkUtils().isInternetConnected(this)){
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_LONG).show()
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()

    }


}