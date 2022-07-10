package com.example.newsapp.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.model.ArticlePresentation


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()
    lateinit var article: ArticlePresentation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        article = args.article
        bindArticleDetails()
    }

    private fun bindArticleDetails() = with(binding) {
        Glide.with(this@DetailsFragment)
            .load(article.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivArticle)
        tvName.text = article.title
        tvAuthor.text = "by ${article.authorName}"
        tvDate.text = article.publishedAt
        tvDescription.text = article.description

        btnWebsite.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.websiteUrl)
                )
            )
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_details
}