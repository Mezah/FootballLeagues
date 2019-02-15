package com.example.footballleagues.repository.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.footballleagues.repository.local.app.TeamEntity
import io.reactivex.Single

@Dao
interface TeamDao {

    @Insert
    fun insertAllTeams(teams: List<TeamEntity>)

    @Query("SELECT * FROM ${TeamEntity.TABLE_NAME} WHERE id =:teamId")
    fun queryTeamById(teamId: String): Single<TeamEntity>
}