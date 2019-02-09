package com.example.footballleagues

import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.data.api.TeamsResponse
import com.example.footballleagues.repository.remote.FootballWebClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiCallingTest {

    private lateinit var client: FootballWebClient

    @Before
    fun setup() {
        client = FootballWebClient.getMyStoreServices()
    }

    @Test
    fun TestLeaguesAPI() {

        runBlocking {
            getLeaguesList()
        }
    }

    @Test
    fun TestTeamsInLeagueAPI() {

        runBlocking {
            val leagues = getLeaguesList()
            val leagueId = leagues.body()?.competitions?.get(0)?.id
            getTeamsInLeague(leagueId.toString())
        }
    }

    @Test
    fun TestTeamInfoAPI() {

        runBlocking {
            val leagues = getLeaguesList()
            val leagueId = leagues.body()?.competitions?.get(0)?.id
            val league = getTeamsInLeague(leagueId.toString())
            val teamId = league.body()?.competition?.id
            val team = client.getTeamInformation(teamId.toString()).await()
            Assert.assertTrue("Error Response Failed", team.code() == 200)

            Assert.assertNotNull("Error Team is Null", team)
            Assert.assertNotNull("Error No Teams from API", team.body()?.id)
        }
    }

    suspend fun getLeaguesList(): Response<LeaguesResponse> {
        val response = client.getTierOneLeaguesList().await()
        Assert.assertTrue("Error Response Failed", response.code() == 200)
        Assert.assertNotNull("Error Response is Null", response.body())
        Assert.assertTrue("Error No Leagues from API", response.body()?.count ?: 0 > 0)
        return response
    }

    suspend fun getTeamsInLeague(leagueId: String): Response<TeamsResponse> {
        val teamsInLeague = client.getTeamsListInLeagues(leagueId).await()
        Assert.assertTrue("Error Response Failed", teamsInLeague.code() == 200)
        Assert.assertNotNull("Error Response is Null", teamsInLeague)
        Assert.assertTrue("Error No Teams from API", teamsInLeague.body()?.count ?: 0 > 0)
        return teamsInLeague
    }
}
