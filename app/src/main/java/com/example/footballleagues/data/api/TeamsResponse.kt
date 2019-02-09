package com.example.footballleagues.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamsResponse {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("competition")
    @Expose
    var competition: Competition? = null
    @SerializedName("season")
    @Expose
    var season: Season? = null
    @SerializedName("teams")
    @Expose
    var teams: List<Team>? = null
}


class Team {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("area")
    @Expose
    var area: Area? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("shortName")
    @Expose
    var shortName: String? = null
    @SerializedName("tla")
    @Expose
    var tla: String? = null
    @SerializedName("crestUrl")
    @Expose
    var crestUrl: Any? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("founded")
    @Expose
    var founded: Int? = null
    @SerializedName("clubColors")
    @Expose
    var clubColors: String? = null
    @SerializedName("venue")
    @Expose
    var venue: String? = null
    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String? = null
}
