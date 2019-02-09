package com.example.footballleagues.di

import com.example.footballleagues.repository.remote.FootballWebClient
import org.koin.dsl.module.module

val apiModule = module {
    // create web client
    single { FootballWebClient.getMyStoreServices() }
}


