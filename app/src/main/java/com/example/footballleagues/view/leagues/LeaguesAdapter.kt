package com.example.footballleagues.view.leagues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagues.R
import com.example.footballleagues.databinding.ItemLeagueBinding
import com.example.footballleagues.repository.local.app.LeagueEntity

class LeaguesAdapter : ListAdapter<LeagueEntity, LeaguesVH>(LeaguesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesVH {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding: ItemLeagueBinding = DataBindingUtil.inflate(inflater, R.layout.item_league, parent, false)

        return LeaguesVH(viewBinding)
    }

    override fun onBindViewHolder(holder: LeaguesVH, position: Int) {
        val item = getItem(position)
        holder.bindData(item, createOnClickListener(item.leagueId.toString(), item.leagueName ?: ""))
    }

    private fun createOnClickListener(id: String, title: String): View.OnClickListener {
        return View.OnClickListener {
            val toTeamsScreen = LeaguesFragmentDirections.actionLeaguesFragmentToTeamsFragment(id, title)
            it.findNavController().navigate(toTeamsScreen)
        }
    }
}

class LeaguesVH(private val view: ItemLeagueBinding) : RecyclerView.ViewHolder(view.root) {

    fun bindData(leagueEntity: LeagueEntity, clickListener: View.OnClickListener) {
        view.apply {
            league = leagueEntity
            goToTeams = clickListener
            executePendingBindings()
        }
    }
}

class LeaguesDiffUtil : DiffUtil.ItemCallback<LeagueEntity>() {
    override fun areItemsTheSame(oldItem: LeagueEntity, newItem: LeagueEntity): Boolean {
        return oldItem.leagueId == newItem.leagueId
    }

    override fun areContentsTheSame(oldItem: LeagueEntity, newItem: LeagueEntity): Boolean {
        return oldItem.leagueId == newItem.leagueId
    }

}