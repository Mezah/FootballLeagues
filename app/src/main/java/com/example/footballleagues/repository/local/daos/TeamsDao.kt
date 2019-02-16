package com.example.footballleagues.repository.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.footballleagues.repository.local.entities.TeamsEntity
import io.reactivex.Single

@Dao
interface TeamsDao {

    @Insert
    fun insertAllTeams(teams: List<TeamsEntity>)

    @Query("SELECT * FROM ${TeamsEntity.TABLE_NAME} WHERE  ${TeamsEntity.LEAGUE_ID} = :leagueId")
    fun queryTeamsByLeagueId(leagueId: String): Single<List<TeamsEntity>>

    @Query("SELECT * FROM ${TeamsEntity.TABLE_NAME} WHERE  teamId = :teamId")
    fun queryTeamsById(teamId: String):Single<TeamsEntity>
}