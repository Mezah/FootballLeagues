package com.example.footballleagues.vm.teamvm

import androidx.lifecycle.LiveData
import com.example.footballleagues.common.CustomDisposable
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.common.UseCase
import com.example.footballleagues.repository.local.app.LeagueEntity
import com.example.footballleagues.repository.local.entities.TeamsEntity
import com.example.footballleagues.repository.local.repos.LeagueRepository
import com.example.footballleagues.repository.local.repos.TeamsRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class TeamsUseCase(private val teamsRepository: TeamsRepository
,private val leagueRepository: LeagueRepository):UseCase() {

    fun loadTeamsInLeague(leagueID:String):LiveData<FBResult<List<TeamsEntity>>>{
        return teamsRepository.getTeamsList(leagueID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(CustomDisposable<List<TeamsEntity>>(true)).getCallingResult()
    }

    fun loadLeagueData(leagueID: String):LiveData<FBResult<LeagueEntity>>{
        return leagueRepository.getLeagueById(leagueID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(CustomDisposable<LeagueEntity>(true)).getCallingResult()
    }
}

