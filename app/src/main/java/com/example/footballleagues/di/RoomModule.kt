package com.example.footballleagues.di

import androidx.room.Room
import com.example.footballleagues.repository.local.database.FootballLeaguesDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), FootballLeaguesDB::class.java, "leagues-db")
            .build()
    }
    // get leagues table
    single { get<FootballLeaguesDB>().leaguesDao()}
}