package com.example.footballleagues.vm.leaguesvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.repository.local.app.LeagueEntity

class LeaguesViewModel(
    private val leaguesUseCase: LeaguesUseCase
) : ViewModel() {

    private val league = MutableLiveData<FBResult<LeagueEntity>>()


    val singleLeagueLiveData: LiveData<FBResult<LeagueEntity>>
        get() = league

    fun loadLeaguesList() :LiveData<FBResult<List<LeagueEntity>>>{
        return  leaguesUseCase.getLeaguesList()
    }

//    fun loadLeagueById(id: String) {
//        league.postValue(leaguesUseCase.getLeagueById(id))
//    }
}