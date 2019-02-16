package com.example.footballleagues.repository.local.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.data.api.TeamsResponse
import com.example.footballleagues.repository.local.app.LeagueEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class LeagueEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var leagueId: Int? = null

    var leagueName: String? = null

    var leagueEmblem: String? = null

    var winner: String? = null

    var winnerEmblem: String? = null

    companion object {
        fun mapFromResponse(leaguesResponse: LeaguesResponse): List<LeagueEntity> {
            val list = arrayListOf<LeagueEntity>()
            leaguesResponse.competitions?.let { competitions ->
                for (l in competitions) {
                    list.add(LeagueEntity().apply {
                        leagueId = l.id
                        leagueName = l.name
                        leagueEmblem = l.emblemUrl
                        winner = l.currentSeason?.winner?.shortName
                        winnerEmblem = l.currentSeason?.winner?.crestUrl
                    })
                }
            }
            return list
        }

        fun mapFromResponse(teamsResponse: TeamsResponse):LeagueEntity?{
            return teamsResponse.competition?.let{competition ->
                return@let LeagueEntity().apply {
                    leagueId = teamsResponse.competition?.id
                    leagueName = teamsResponse.competition?.name
                    leagueEmblem = teamsResponse.competition?.emblemUrl
                    winner = teamsResponse.competition?.currentSeason?.winner?.shortName
                    winnerEmblem = teamsResponse.competition?.currentSeason?.winner?.crestUrl
                }
            }
        }

        const val TABLE_NAME = "Leagues"
    }

}