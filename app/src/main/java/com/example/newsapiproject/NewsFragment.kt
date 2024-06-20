package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.databinding.FragmentNewsBinding
import com.example.newsapiproject.presentation.adapter.NewsAdapter
import com.example.newsapiproject.presentation.viewModel.NewsViewModel
import kotlin.math.log

private const val TAG = "NewsFragment"
class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)

        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            Log.d(TAG, "viewNewsList: response: $response")
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()

                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }

                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun initRecyclerView() {
     //   newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        fragmentNewsBinding.progressBar2.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        fragmentNewsBinding.progressBar2.visibility = View.INVISIBLE
    }


}