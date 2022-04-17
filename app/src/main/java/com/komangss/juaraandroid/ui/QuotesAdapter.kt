package com.komangss.juaraandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.komangss.core.domain.model.Quote
import com.komangss.juaraandroid.databinding.ItemQuoteBinding

class QuotesAdapter(private val onClick : (Quote) -> Unit) : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {

    private val quoteList = ArrayList<Quote>()

    fun setQuotes(newQuoteList: List<Quote>) {
        val diff = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return quoteList[oldItemPosition]._id == newQuoteList[newItemPosition]._id
            }
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return quoteList[oldItemPosition] == newQuoteList[newItemPosition]
            }
            override fun getOldListSize() = quoteList.size
            override fun getNewListSize() = newQuoteList.size
        }
        val diffResult = DiffUtil.calculateDiff(diff)
        this.quoteList.clear()
        this.quoteList.addAll(newQuoteList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int = quoteList.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quoteList[position])
    }

    class QuoteViewHolder(private val binding: ItemQuoteBinding, private val onClick: (Quote) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            binding.content.text = quote.content
            binding.author.text = quote.author

            binding.root.setOnClickListener {
                onClick(quote)
            }
        }
    }

}