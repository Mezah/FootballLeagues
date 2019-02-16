package com.example.footballleagues.repository.local.repos

import com.example.footballleagues.repository.local.entities.TeamsEntity
import io.reactivex.Single

interface ITeamsRepository {

    fun getTeamsList(leagueId: String): Single<List<TeamsEntity>>

    fun getTeamById(teamId: String): Single<TeamsEntity>
}