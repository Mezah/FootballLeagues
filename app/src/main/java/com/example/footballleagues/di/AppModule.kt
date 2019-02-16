package com.example.footballleagues.di

import com.example.footballleagues.repository.local.repos.LeagueRepository
import com.example.footballleagues.repository.local.repos.TeamsRepository
import com.example.footballleagues.vm.leaguesvm.LeaguesUseCase
import com.example.footballleagues.vm.leaguesvm.LeaguesViewModel
import com.example.footballleagues.vm.teamvm.TeamsUseCase
import com.example.footballleagues.vm.teamvm.TeamsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // leagues viewModel
    viewModel { LeaguesViewModel(get()) }
    viewModel { TeamsViewModel(get()) }

    // leagues Repository
    single(createdAtStart = true) {
        LeagueRepository(get(), get())
    }

    // Teams Repository
    single(createdAtStart = true) {
        TeamsRepository(get(), get())
    }
    // leagues Use case
    single(createdAtStart = true) {
        LeaguesUseCase(get())
    }

    // leagues Use case
    single(createdAtStart = true) {
        TeamsUseCase(get(),get())
    }
}