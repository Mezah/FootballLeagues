package com.example.footballleagues.vm.leaguesvm

import androidx.lifecycle.ViewModel
import com.example.footballleagues.repository.local.repos.LeagueRepository

class LeaguesViewModel(
    private val leaguesRepository: LeagueRepository
):ViewModel() {

}