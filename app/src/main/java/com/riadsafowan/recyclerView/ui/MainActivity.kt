package com.riadsafowan.recyclerView.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riadsafowan.recyclerView.data.remote.Resource
import com.riadsafowan.recyclerView.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.listItemsLiveData.observe(this) { result ->
            when (result) {
                is Resource.Failure -> {
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                    //handle failure case here
                }
                Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    recyclerViewAdapter.items = result.value
                }
            }

        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerViewAdapter
        }

        recyclerViewAdapter.itemClickListener = { view, item, position ->
            when (item) {
                is RecyclerViewItem.Director -> Toast.makeText(
                    this,
                    "Director clicked",
                    Toast.LENGTH_SHORT
                ).show()
                is RecyclerViewItem.Movie -> Toast.makeText(
                    this,
                    "Movie clicked",
                    Toast.LENGTH_SHORT
                ).show()
                is RecyclerViewItem.Title -> Toast.makeText(
                    this,
                    "Title clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.apply {
            button1.setOnClickListener {
                viewModel.setdata()
            }
            button2.setOnClickListener {k->
                viewModel.getData()?.forEach {it->
                    Toast.makeText(
                        this@MainActivity,
                        it.firstName + " " + it.lastName,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }
}