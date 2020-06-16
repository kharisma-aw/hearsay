package com.awkris.hearsay.presentation.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.awkris.hearsay.R
import com.awkris.hearsay.data.model.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_refreshable_list.*
import timber.log.Timber

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_refreshable_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh.isEnabled = false
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = HeadlinesAdapter()
            .apply {
                itemNewsClickListener = object : ItemNewsClickListener {
                    override fun onItemClicked(url: String) {
                        Timber.d("redirecting to url: $url")
                        val action = HeadlinesFragmentDirections.actionViewarticle(url)
                        findNavController(this@HeadlinesFragment).navigate(action)
                    }
                }
            }

        viewModel.headlines.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            adapter.setNetworkState(it)
            if (it is NetworkState.Error) Timber.d("Error encountered with message: ${it.message}")
            when (it) {
                is NetworkState.Error, NetworkState.Success -> {
                    if (swipe_refresh.isRefreshing) swipe_refresh.isRefreshing = false
                }
            }
        })

        rcv_list.layoutManager = layoutManager
        rcv_list.adapter = adapter
    }
}