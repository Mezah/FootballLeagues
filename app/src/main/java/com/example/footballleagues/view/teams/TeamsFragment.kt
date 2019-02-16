package com.example.footballleagues.view.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagues.R
import com.example.footballleagues.common.AlertMessages
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.common.bind
import com.example.footballleagues.view.BaseFragment
import com.example.footballleagues.view.team.TeamsAdapter
import com.example.footballleagues.vm.teamvm.TeamsViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class TeamsFragment : BaseFragment() {

    private lateinit var viewBinding: com.example.footballleagues.databinding.LayoutTeamsFragmentBinding

    private val teamsViewModel by viewModel<TeamsViewModel>()
    private val teamsAdapter = TeamsAdapter()
    private lateinit var leagueId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagueId = TeamsFragmentArgs.fromBundle(arguments!!).leagueId


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = bind(R.layout.layout_teams_fragment, container)
        val parent = activity as? AppCompatActivity
        val toolbar = parent?.supportActionBar
        toolbar?.title = TeamsFragmentArgs.fromBundle(arguments!!).leagueName
        viewBinding.apply {
            teamsList.apply {
                adapter = teamsAdapter
                layoutManager = when (resources.getBoolean(R.bool.grid_layout)) {
                    true -> GridLayoutManager(context!!, 2, RecyclerView.VERTICAL, false)
                    false -> LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
                }
                addItemDecoration(DividerItemDecoration(context!!, RecyclerView.VERTICAL))
            }
        }
        teamsViewModel.loadLeaguesInfo(leagueId).observe(viewLifecycleOwner, Observer { league ->
            when (league) {
                is FBResult.Loading -> {
                    showLoading(league.loading)
                }
                is FBResult.Success -> {
                    showLoading(false)
                    viewBinding.apply {
                        this.league = league.data
                        executePendingBindings()
                    }
                }
                is FBResult.Error -> {
                    showLoading(false)
                    AlertMessages.showErrorDialog(context!!, league.error.message ?: "")
                }
            }

        })
        teamsViewModel.loadAllTeamsList(leagueId).observe(viewLifecycleOwner, Observer { teamsList ->
            when (teamsList) {
                is FBResult.Loading -> {
                    showLoading(teamsList.loading)
                }
                is FBResult.Success -> {
                    showLoading(false)
                    teamsAdapter.submitList(teamsList.data)
                }
                is FBResult.Error -> {
                    showLoading(false)
                    AlertMessages.showErrorDialog(context!!, teamsList.error.message ?: "")
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
}