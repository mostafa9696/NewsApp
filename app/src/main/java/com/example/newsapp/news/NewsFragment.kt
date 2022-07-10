package com.example.newsapp.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.model.ArticlePresentation
import com.example.newsapp.model.DataResource
import com.example.newsapp.utils.remove
import com.example.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    private val viewModel: NewsViewModel by viewModels()
    lateinit var adapter: ArticlesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArticles()
    }

    private fun getArticles() {
        viewModel.getArticles()

        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataResource.Loading -> binding.progressbar.show()
                is DataResource.Success -> {
                    binding.progressbar.remove()
                    setNewsList(it.data)
                }
                is DataResource.Error -> {
                    binding.progressbar.remove()
                    showToast(it.error!!)
                }
            }
        }
    }

    private fun setNewsList(articles: List<ArticlePresentation>?) {
        articles?.let {
            adapter = ArticlesAdapter(articles) {
                val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
            binding.articlesRv.adapter = adapter
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_news
}