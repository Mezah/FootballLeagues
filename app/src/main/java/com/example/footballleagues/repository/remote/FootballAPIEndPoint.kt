package com.example.footballleagues.repository.remote

import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.data.api.TeamResponse
import com.example.footballleagues.data.api.TeamsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballAPIEndPoint {

    @GET("competitions/?plan=TIER_ONE")
    fun getLeaguesList(): Deferred<Response<LeaguesResponse>>

    @GET("competitions/{id}/teams")
    fun getTeamsListInLeague(
        @Header("X-Auth-Token") auth: String,
        @Path("id") leagueID: String
    ): Deferred<Response<TeamsResponse>>

    @GET("teams/{id}")
    fun getTeamInformation(
        @Header("X-Auth-Token") auth: String,
        @Path("id") teamId: String
    ): Deferred<Response<TeamResponse>>
}