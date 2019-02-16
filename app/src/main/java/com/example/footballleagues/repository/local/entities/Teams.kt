package com.example.footballleagues.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballleagues.data.api.TeamsResponse

@Entity(tableName = TeamsEntity.TABLE_NAME)
class TeamsEntity {
    @PrimaryKey
    @ColumnInfo(name = "teamId")
    var teamId: Int? = null

    var teamName: String? = null

    var teamLogo: String? = null

    @ColumnInfo(name = LEAGUE_ID)
    var leagueId: Int? = null

    companion object {
        fun mapFromResponse(teamResponse: TeamsResponse): List<TeamsEntity> {
            var list = teamResponse.teams?.map { responseTeam ->
                TeamsEntity().apply {
                    teamId = responseTeam.id
                    teamName = responseTeam.name
                    teamLogo = responseTeam.crestUrl
                    leagueId = teamResponse.competition?.id
                }
            }
            return list ?: listOf()
        }

        const val TABLE_NAME = "League_Teams"
        const val LEAGUE_ID = "league_id"
    }

}