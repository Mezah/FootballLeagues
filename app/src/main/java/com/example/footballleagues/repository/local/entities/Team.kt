package com.example.footballleagues.repository.local.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballleagues.data.api.TeamResponse
import com.example.footballleagues.repository.local.app.TeamEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class TeamEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var teamId: Int? = null

    var teamName: String? = null

    var teamLogo: String? = null

    companion object {
        fun mapFromResponse(teamResponse: TeamResponse) = TeamEntity().apply {
            teamId = teamResponse.id
            teamName = teamResponse.name
            teamLogo = teamResponse.crestUrl
        }

        const val TABLE_NAME = "Teams"
    }
}