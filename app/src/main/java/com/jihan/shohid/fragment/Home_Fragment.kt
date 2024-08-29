package com.jihan.shohid.fragment

import NetworkUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.jihan.shohid.MyApplication
import com.jihan.shohid.R
import com.jihan.shohid.adapter.MyAdapter
import com.jihan.shohid.databinding.FragmentHomeBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory

class Home_Fragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private lateinit var viewModel: ShohidViewModel
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = (activity?.application as MyApplication).repository
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Initialize adapter
        adapter = MyAdapter()
        binding.recyclerView.adapter = adapter

        // Observe LiveData
        viewModel.shoidList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // Set up SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Load Image Slider
        loadImageSlider()

        // Check network connection
        val message = if (NetworkUtils().isInternetConnected(requireContext())) {
            "Internet Connected"
        } else {
            "Internet Not Connected"
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

        return binding.root
    }

    private fun loadImageSlider() {
        val imageList = listOf(
            SlideModel("https://ecdn.dhakatribune.net/contents/cache/images/640x359x1/uploads/media/2024/07/26/Saeed-2c71ff420cb36f119ce1a6fb859ab92e.JPG", "Abu Sayed Quota Movement Bangladesh 2024"),
            SlideModel("https://thediplomat.com/wp-content/uploads/2024/07/sizes/medium/thediplomat_2024-07-19-171437.jpg", "Students clash with riot police during a protest against a quota system for government jobs, in Dhaka, Bangladesh, July 18, 2024."),
            SlideModel("https://ecdn.dhakatribune.net/contents/cache/images/640x359x1/uploads/media/2024/07/06/Protest-e33e908323685701b4b479714cc12069.jpg", "The image shows students and job seekers protesting for cancelling the quota system in government jobs in Dhaka on Saturday, July 6, 2024."),
            SlideModel("https://www.thedailystar.net/sites/default/files/images/2024/08/05/quota-protest5.jpg", "Long March TO Dhaka"),
            SlideModel("https://images.prothomalo.com/prothomalo-english%2F2024-08-06%2Fb1u6cz56%2Fprothomalo-bangla_2024-08_4552dc11-75f7-4590-81ef-08237f41beb9_P1-1100940.jpg", "05 August , The day of Victory")
        )
        binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
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
