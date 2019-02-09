package com.example.footballleagues.data.api

import com.google.gson.annotations.SerializedName

class TeamResponse {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("area")
    var area: Area? = null
    @SerializedName("activeCompetitions")
    var activeCompetitions: List<ActiveCompetition>?=null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("shortName")
    var shortName: String? = null
    @SerializedName("tla")
    var tla: String? = null
    @SerializedName("crestUrl")
    var crestUrl: String? = null
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
    @SerializedName("squad")
    var squad: List<Squad>?=null
    @SerializedName("lastUpdated")
    var lastUpdated: String? =null
}

data class ActiveCompetition(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("area")
    var area: Area?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("plan")
    var plan: String?,
    @SerializedName("lastUpdated")
    var lastUpdated: String?
)

data class Squad(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("position")
    var position: String?,
    @SerializedName("dateOfBirth")
    var dateOfBirth: String?,
    @SerializedName("countryOfBirth")
    var countryOfBirth: String?,
    @SerializedName("nationality")
    var nationality: String?,
    @SerializedName("shirtNumber")
    var shirtNumber: Int?,
    @SerializedName("role")
    var role: String?
)
