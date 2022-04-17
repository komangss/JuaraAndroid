package com.komangss.juaraandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.komangss.core.data.QuotableRepository
import com.komangss.core.data.Resource
import com.komangss.core.domain.model.Quote
import com.komangss.core.domain.repository.IQuotableRepository
import com.komangss.juaraandroid.R
import com.komangss.juaraandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    @Inject
    lateinit var repository: IQuotableRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPagerActivityHome.adapter = sectionsPagerAdapter
        binding.tabsActivityHome.setupWithViewPager(binding.viewPagerActivityHome)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Quotable List"
//        lifecycleScope.launchWhenCreated {
//            repository.favoriteThisQuote(Quote("id", "content", "author", "authorSlug", 1))
//            repository.getQuotes().collect {
//                when(it) {
//                    is Resource.Error -> {
//
//                    }
//                    Resource.InProgress -> {
//
//                    }
//                    is Resource.Success -> {
//                        Log.d("hasil", it.data.toString())
//                    }
//                }
//            }
//            repository.getFavoriteQuotes().collect {
//                when(it) {
//                    is Resource.Error -> {
//
//                    }
//                    Resource.InProgress -> {
//
//                    }
//                    is Resource.Success -> {
//                        Log.d("hasil", it.data.toString())
//                    }
//                }
//            }
//        }
    }


}