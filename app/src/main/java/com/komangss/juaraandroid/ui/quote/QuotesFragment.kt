package com.komangss.juaraandroid.ui.quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
    ): View? {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val quotesAdapter = QuotesAdapter()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}