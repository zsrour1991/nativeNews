package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var isScrolling =false
    private var isLoading =false
    private var isLastPage =false
    private var pages =0

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
                        if(it.totalResults%20 == 0)
                         pages= it.totalResults/20
                        else
                            pages=it.totalResults/20+1

                        isLastPage = page==pages

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
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }

    private fun showProgressBar() {
        isLoading=true
        fragmentNewsBinding.progressBar2.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading=false
        fragmentNewsBinding.progressBar2.visibility = View.INVISIBLE
    }
    private val onScrollListener= object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling=true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager=fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeIfTheCurrentList=layoutManager.itemCount
            val visibleItems=layoutManager.childCount
            val topPosition=layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd=topPosition+visibleItems >= sizeIfTheCurrentList
            val shouldPaginate= !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }

        }


    }


}