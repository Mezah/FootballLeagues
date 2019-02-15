package com.example.footballleagues.vm.leaguesvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleagues.common.Event
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.repository.local.app.LeagueEntity

class LeaguesViewModel(
    private val leaguesUseCase: LeaguesUseCase
) : ViewModel() {

    private val leaguesList = MutableLiveData<Event<FBResult<List<LeagueEntity>>>>()
    private val league = MutableLiveData<Event<FBResult<LeagueEntity>>>()

    val leaguesListLiveData: LiveData<Event<FBResult<List<LeagueEntity>>>>
        get() = leaguesList

    val singleLeagueLiveData: LiveData<Event<FBResult<LeagueEntity>>>
        get() = league

    fun loadLeaguesList() {
        leaguesList.postValue(Event(leaguesUseCase.getLeaguesList()))
    }

    fun loadLeagueById(id: String) {
        league.postValue(Event(leaguesUseCase.getLeagueById(id)))
    }
}