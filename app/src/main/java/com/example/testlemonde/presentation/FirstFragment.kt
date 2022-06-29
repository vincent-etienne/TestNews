package com.example.testlemonde.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlemonde.R
import com.example.testlemonde.databinding.FragmentFirstBinding
import com.example.testlemonde.presentation.news.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding

    private val newsAdapter = NewsAdapter()
    private lateinit var newsViewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        setupRecyclerView()

        newsViewModel.newsState.observe(viewLifecycleOwner, ::handleNewsState)
    }

    private fun handleNewsState(newsState: NewsState) {
        binding.newsLoading.isVisible = newsState is NewsLoading
        binding.newsError.isVisible = newsState is NewsError

        when(newsState){
            NewsError -> Unit // Already handled before
            NewsLoading -> Unit // Already handled before
            is NewsSuccess -> showNews(newsState.newsUi)
        }
    }

    private fun showNews(newsUi: List<NewsUi>) {
        newsAdapter.submitList(newsUi)
    }

    private fun setupRecyclerView() {
        binding.newsRecyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}