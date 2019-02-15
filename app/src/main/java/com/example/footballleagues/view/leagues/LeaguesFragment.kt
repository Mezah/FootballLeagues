package com.example.footballleagues.view.leagues

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagues.R
import com.example.footballleagues.common.AlertMessages
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.common.bind
import com.example.footballleagues.databinding.FragmentLeaguesBinding
import com.example.footballleagues.view.BaseFragment
import com.example.footballleagues.vm.leaguesvm.LeaguesViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class LeaguesFragment : BaseFragment() {

    private val leaguesViewModel by viewModel<LeaguesViewModel>()
    private lateinit var viewBinding: FragmentLeaguesBinding
    private val leaguesAdapter = LeaguesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = bind(R.layout.fragment_leagues, container)
        viewBinding.leaguesList.apply {
            layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
            adapter = leaguesAdapter
        }

        leaguesViewModel.loadLeaguesList().observe(viewLifecycleOwner, Observer { listResult ->
            when (listResult) {
                is FBResult.Loading -> {
                    showLoading(listResult.loading)
                }
                is FBResult.Success -> {
                    showLoading(false)
                    leaguesAdapter.submitList(listResult.data)
                }
                is FBResult.Error -> {
                    showLoading(false)
                    AlertMessages.showErrorDialog(context!!, listResult.error.message ?: "")
                }
            }
        })
        return viewBinding.root
    }

    private fun showLoading(show: Boolean) {
        if (show)
            viewBinding.progressBar.visibility = View.VISIBLE
        else
            viewBinding.progressBar.visibility = View.GONE

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}