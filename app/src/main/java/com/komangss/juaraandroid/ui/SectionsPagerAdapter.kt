package com.komangss.juaraandroid.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.komangss.juaraandroid.R
import com.komangss.juaraandroid.ui.favoritequote.FavoriteQuotesFragment
import com.komangss.juaraandroid.ui.quote.QuotesFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.quotes, R.string.favorite_quotes)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> QuotesFragment()
            1 -> FavoriteQuotesFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}