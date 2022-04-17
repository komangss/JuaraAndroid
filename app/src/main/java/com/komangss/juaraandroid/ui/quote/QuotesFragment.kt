package com.komangss.juaraandroid.ui.quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.komangss.core.data.Resource
import com.komangss.juaraandroid.R
import com.komangss.juaraandroid.databinding.FragmentQuotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : Fragment() {

    val viewModel by viewModels<QuotesViewModel>()
    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val quotesAdapter = QuotesAdapter()
            viewModel.quoteList.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        quotesAdapter.setQuotes(it.data.results)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                    }
                    Resource.InProgress -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                }
            }

            with(binding.rvQuote) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = quotesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}