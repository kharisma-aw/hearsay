package com.awkris.hearsay.presentation.headlines

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.awkris.hearsay.data.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class LoadingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class HeadlinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: Article, itemNewsClickListener: ItemNewsClickListener) {
        itemView.txt_title.text = data.title
        itemView.txt_date.text = data.publishedAt
        when(data.urlToImage.isNullOrBlank()) {
            true -> itemView.img_cover.visibility = View.GONE
            false -> itemView.img_cover.load(data.urlToImage) {
                transformations(RoundedCornersTransformation(8f))
            }
        }
        itemView.setOnClickListener { itemNewsClickListener.onItemClicked(data.url) }
    }
}

interface ItemNewsClickListener {
    fun onItemClicked(url: String)
}