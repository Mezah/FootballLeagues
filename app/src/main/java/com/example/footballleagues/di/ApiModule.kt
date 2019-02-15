package com.example.footballleagues.di

import com.example.footballleagues.repository.remote.FootballWebClient
import org.koin.dsl.module

val apiModule = module {
    // create web client
    single(createdAtStart = true) { FootballWebClient.getMyStoreServices() }
}


