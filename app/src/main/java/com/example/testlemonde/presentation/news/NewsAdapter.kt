package com.example.testlemonde.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlemonde.databinding.NewsItemBinding

class NewsAdapter : ListAdapter<NewsUi, NewsAdapter.NewsItemViewHolder>(NewsItemViewHolder.NewsItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsItemViewHolder(
        private val binding: NewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsUi: NewsUi) {
            binding.newsTitle.text = newsUi.title
        }

        class NewsItemDiff : DiffUtil.ItemCallback<NewsUi>() {
            override fun areItemsTheSame(oldItem: NewsUi, newItem: NewsUi): Boolean {
                return false //TODO
            }

            override fun areContentsTheSame(oldItem: NewsUi, newItem: NewsUi): Boolean {
                return false //TODO
            }

            //TODO Change Payload
        }
    }
}