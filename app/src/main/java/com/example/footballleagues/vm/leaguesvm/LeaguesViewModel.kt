package com.example.footballleagues.vm.leaguesvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.repository.local.app.LeagueEntity

class LeaguesViewModel(
    private val leaguesUseCase: LeaguesUseCase
) : ViewModel() {

    fun loadLeaguesList() :LiveData<FBResult<List<LeagueEntity>>>{
        return  leaguesUseCase.getLeaguesList()
    }

}