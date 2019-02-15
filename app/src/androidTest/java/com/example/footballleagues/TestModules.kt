package com.example.footballleagues

import androidx.room.Room
import com.example.footballleagues.repository.local.database.FootballLeaguesDB
import org.koin.dsl.module

// Room In memroy database
val roomTestingModule = module(override = true) {
    single {
        Room.inMemoryDatabaseBuilder(get(), FootballLeaguesDB::class.java)
            .allowMainThreadQueries()
            .build()
    }
}