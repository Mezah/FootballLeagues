package com.example.footballleagues.repository.local.repos

import com.example.footballleagues.repository.local.app.LeagueEntity
import io.reactivex.Single

interface ILeaguesRepos {

    fun getLeaguesList(): Single<List<LeagueEntity>>

    fun getLeagueById(leagueId: String): Single<LeagueEntity>
}