package com.example.footballleagues.vm.leaguesvm

import androidx.lifecycle.LiveData
import com.example.footballleagues.common.CustomDisposable
import com.example.footballleagues.common.FBResult
import com.example.footballleagues.common.UseCase
import com.example.footballleagues.repository.local.app.LeagueEntity
import com.example.footballleagues.repository.local.repos.LeagueRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class LeaguesUseCase(private val leaguesRepository: LeagueRepository) : UseCase() {

   fun getLeagueById(id:String): LiveData<FBResult<LeagueEntity>> {
       return leaguesRepository.getLeagueById(id)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeWith(CustomDisposable<LeagueEntity>(true)).getCallingResult()
   }


    fun getLeaguesList(): LiveData<FBResult<List<LeagueEntity>>> {
        return leaguesRepository.getLeaguesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(CustomDisposable<List<LeagueEntity>>(true)).getCallingResult()
    }
}