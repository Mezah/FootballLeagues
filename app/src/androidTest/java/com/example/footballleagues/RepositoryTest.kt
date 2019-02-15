package com.example.footballleagues

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.footballleagues.repository.local.repos.LeagueRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class RepositoryTest : KoinTest {
    val leagueRepository: LeagueRepository by inject()

    @Before
    fun setup() {
        loadKoinModules(roomTestingModule)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testLeaguesRepos() {
        val list = leagueRepository.getLeaguesList().blockingGet()
        Assert.assertFalse("Error Empty list", list.isEmpty())

        // get league by id
        val firstLeagueId = list[0].leagueId

        val firstLeague = leagueRepository.getLeagueById(firstLeagueId.toString()).blockingGet()

        Assert.assertNotNull("Error: this object suppose to have a value", firstLeague)

        Assert.assertTrue("Error: Id should not be negative", firstLeague.leagueId != -1)
    }


}