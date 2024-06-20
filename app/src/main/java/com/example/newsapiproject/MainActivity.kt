package com.example.newsapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapiproject.databinding.ActivityMainBinding
import com.example.newsapiproject.presentation.adapter.NewsAdapter
import com.example.newsapiproject.presentation.viewModel.NewsViewModel
import com.example.newsapiproject.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController=navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)

        viewModel = ViewModelProvider(this,factory)
            .get(NewsViewModel::class.java)

    }
}