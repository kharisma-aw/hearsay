package com.awkris.hearsay.presentation.headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awkris.hearsay.R
import com.awkris.hearsay.data.model.Article
import com.awkris.hearsay.data.model.NetworkState

class HeadlinesAdapter : PagedListAdapter<Article, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    lateinit var itemNewsClickListener: ItemNewsClickListener
    private lateinit var networkState: NetworkState

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            ItemType.LoadingProgress.ordinal
        } else {
            ItemType.ItemMovie.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            ItemType.LoadingProgress.ordinal -> {
                LoadingItemViewHolder(inflater.inflate(R.layout.item_loading, parent, false))
            }
            ItemType.ItemMovie.ordinal -> {
                HeadlinesViewHolder(inflater.inflate(R.layout.item_news, parent, false))
            }
            else -> throw UnsupportedOperationException("Unknown viewtype")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeadlinesViewHolder -> {
                holder.bind(requireNotNull(getItem(position)), itemNewsClickListener)
            }
            is LoadingItemViewHolder -> {}
        }
    }

    fun setNetworkState(state: NetworkState) {
        val previousState = if (this::networkState.isInitialized) networkState else null
        val hadExtraRow = hasExtraRow()
        networkState = state
        val hasExtraRow = hasExtraRow()
        if (hasExtraRow != hadExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != state) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow(): Boolean {
        return this::networkState.isInitialized && networkState == NetworkState.Loading
    }

    enum class ItemType {
        LoadingProgress,
        ItemMovie
    }
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.url == newItem.url &&
                oldItem.publishedAt == newItem.publishedAt
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

