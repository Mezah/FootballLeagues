package com.example.footballleagues.data.api

import com.google.gson.annotations.SerializedName

class TeamsResponse {
    @SerializedName("count")
    var count: Int? = null
    @SerializedName("competition")
    var competition: Competition? = null
    @SerializedName("season")
    var season: Season? = null
    @SerializedName("teams")
    var teams: List<Team>? = null
}


class Team {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("area")
    var area: Area? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("shortName")
    var shortName: String? = null
    @SerializedName("tla")
    var tla: String? = null
    @SerializedName("crestUrl")
    var crestUrl: Any? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("website")
    var website: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("founded")
    var founded: Int? = null
    @SerializedName("clubColors")
    var clubColors: String? = null
    @SerializedName("venue")
    var venue: String? = null
    @SerializedName("lastUpdated")
    var lastUpdated: String? = null
}
