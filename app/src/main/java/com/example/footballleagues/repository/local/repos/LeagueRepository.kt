package com.example.footballleagues.repository.local.repos

import com.example.footballleagues.repository.local.app.LeagueEntity
import com.example.footballleagues.repository.local.daos.LeaguesDao
import com.example.footballleagues.repository.remote.FootballWebClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LeagueRepository(
    private val webClient: FootballWebClient,
    private val leagueDao: LeaguesDao
) : ILeaguesRepos {
    override fun getLeagueById(leagueId: String): Single<LeagueEntity> {
        return leagueDao.findLeagueById(leagueId)
            .subscribeOn(Schedulers.io())
    }

    override fun getLeaguesList(): Single<List<LeagueEntity>> {
        return leagueDao.getLeagues().flatMap { list ->
            if (list.isNullOrEmpty()) {
                // nothing in database so call backend
                return@flatMap webClient.getTierOneLeaguesList().map { response ->
                    val remoteList = LeagueEntity.mapFromResponse(response)
                    leagueDao.insertLeagues(remoteList)
                    return@map remoteList
                }
            } else {
                return@flatMap Single.just(list)
            }
        }.subscribeOn(Schedulers.io())
    }

}