package com.example.footballleagues.di

import com.example.footballleagues.repository.local.repos.LeagueRepository
import com.example.footballleagues.vm.leaguesvm.LeaguesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // leagues viewModel
    viewModel { LeaguesViewModel(get()) }
    // leagues Repository
    single(createdAtStart = true) {
        LeagueRepository(get(), get())
    }
}