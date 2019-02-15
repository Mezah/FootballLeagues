package com.example.footballleagues.repository.local.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.repository.local.app.LeagueEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class LeagueEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var leagueId: Int? = null

    var leagueName: String? = null

    var leagueEmblem: String? = null

    companion object {
        fun mapFromResponse(leaguesResponse: LeaguesResponse): List<LeagueEntity> {
            val list = arrayListOf<LeagueEntity>()
            leaguesResponse.competitions?.let { competitions ->
                for (l in competitions) {
                    list.add(LeagueEntity().apply {
                        leagueId = l.id
                        leagueName = l.name
                        leagueEmblem = l.emblemUrl
                    })
                }
            }
            return list
        }

        const val TABLE_NAME = "Leagues"
    }

}