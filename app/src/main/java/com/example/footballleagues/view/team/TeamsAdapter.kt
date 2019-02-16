package com.example.footballleagues.view.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagues.R
import com.example.footballleagues.databinding.ItemTeamBinding
import com.example.footballleagues.repository.local.entities.TeamsEntity

class TeamsAdapter : ListAdapter<TeamsEntity, TeamsVH>(TeamsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsVH {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding: ItemTeamBinding = DataBindingUtil.inflate(inflater, R.layout.item_team, parent, false)
        return TeamsVH(viewDataBinding)
    }

    override fun onBindViewHolder(holder: TeamsVH, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class TeamsVH(val view: ItemTeamBinding) : RecyclerView.ViewHolder(view.root) {

    fun bindData(team: TeamsEntity) {
        view.apply {
            this.teams = team
            executePendingBindings()
        }
    }
}

class TeamsDiffUtil : DiffUtil.ItemCallback<TeamsEntity>() {
    override fun areItemsTheSame(oldItem: TeamsEntity, newItem: TeamsEntity): Boolean {
        return oldItem.teamId == newItem.teamId
    }

    override fun areContentsTheSame(oldItem: TeamsEntity, newItem: TeamsEntity): Boolean {
        return oldItem.teamId == newItem.teamId
    }

}