package com.example.footballleagues.repository.local.repos

import com.example.footballleagues.repository.local.daos.TeamsDao
import com.example.footballleagues.repository.local.entities.TeamsEntity
import com.example.footballleagues.repository.remote.FootballWebClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TeamsRepository(
    private val webClient: FootballWebClient,
    private val teamsDao: TeamsDao
) : ITeamsRepository {
    override fun getTeamsList(leagueId: String): Single<List<TeamsEntity>> {
        return teamsDao.queryTeamsByLeagueId(leagueId)
            .flatMap { list ->
                if (list.isNullOrEmpty()) {
                    return@flatMap webClient.getTeamsListInLeagues(leagueId)
                        .map {
                            val teams = TeamsEntity.mapFromResponse(it)
                            teamsDao.insertAllTeams(teams)
                            return@map teams
                        }
                } else {
                    return@flatMap Single.just(list)
                }
            }.subscribeOn(Schedulers.io())

    }

    override fun getTeamById(teamId: String): Single<TeamsEntity> {
        return teamsDao.queryTeamsById(teamId)
            .subscribeOn(Schedulers.io())

    }
}