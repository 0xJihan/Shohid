package com.jihan.shohid.fragment

import NetworkUtils
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jihan.shohid.MyApplication
import com.jihan.shohid.adapter.MyAdapter
import com.jihan.shohid.databinding.FragmentHomeBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory
import com.jihan.shohid.room.Shohid


class Home_Fragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private lateinit var viewModel: ShohidViewModel

    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater)


        val repository = (activity?.application as MyApplication).repository

        //viewModel
        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]


        //observing data
        viewModel.shoidList.observe(viewLifecycleOwner) {
            adapter = MyAdapter(viewModel.shoidList.value!!)
            binding.recyclerView.adapter = adapter
        }


        // number changing animation
        animateNumberChange(650, binding.tvTotalShohid)
        animateNumberChange(33000, binding.tvTotalAhoto)
        animateNumberChange(11000, binding.tvTotalNikhoj)


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


        // swipe refresh
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }


        // showing toast based on network connection
        if (NetworkUtils().isInternetConnected(requireContext())) {
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Internet Not Connected", Toast.LENGTH_LONG).show()
        }


        // Inflate the layout for this fragment
        return binding.root
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


    private fun animateNumberChange(endValue: Int, textView: TextView) {
        val animator = ValueAnimator.ofInt(0, endValue)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            textView.text = "$animatedValue +"
        }
        animator.start()
    }

}