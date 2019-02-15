package com.example.footballleagues.repository.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.footballleagues.repository.local.app.LeagueEntity
import io.reactivex.Single

@Dao
interface LeaguesDao {
    @Insert
    fun insertLeagues(leagues: List<LeagueEntity>)

    @Query("SELECT * FROM ${LeagueEntity.TABLE_NAME}")
    fun getLeagues(): Single<List<LeagueEntity>>

    @Query("SELECT * FROM ${LeagueEntity.TABLE_NAME} WHERE id = :id")
    fun findLeagueById(id: String): Single<LeagueEntity>
}