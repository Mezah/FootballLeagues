package com.example.footballleagues.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.footballleagues.repository.local.app.LeagueEntity
import com.example.footballleagues.repository.local.app.TeamEntity
import com.example.footballleagues.repository.local.daos.LeaguesDao
import com.example.footballleagues.repository.local.daos.TeamDao

@Database(entities = [LeagueEntity::class, TeamEntity::class], version = 1)
abstract class FootballLeaguesDB :RoomDatabase(){

    abstract fun teamDao(): TeamDao
    abstract fun leaguesDao(): LeaguesDao

}