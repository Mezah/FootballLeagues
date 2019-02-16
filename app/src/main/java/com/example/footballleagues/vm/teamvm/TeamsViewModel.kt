package com.example.footballleagues.vm.teamvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.repository.local.app.LeagueEntity
import com.example.footballleagues.repository.local.entities.TeamsEntity

class TeamsViewModel(private val teamsUseCase: TeamsUseCase) : ViewModel() {

    fun loadAllTeamsList(leagueId: String): LiveData<FBResult<List<TeamsEntity>>> {
        return teamsUseCase.loadTeamsInLeague(leagueId)
    }

    fun loadLeaguesInfo(leagueId: String): LiveData<FBResult<LeagueEntity>> {
        return teamsUseCase.loadLeagueData(leagueId)
    }
}