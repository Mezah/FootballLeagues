package com.example.footballleagues.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("area")
    @Expose
    var area: Area? = null
    @SerializedName("activeCompetitions")
    @Expose
    var activeCompetitions: List<ActiveCompetition>? = null
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
    var crestUrl: String? = null
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
    @SerializedName("squad")
    @Expose
    var squad: List<Squad>? = null
    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String? = null
}

data class ActiveCompetition(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("area")
    @Expose
    var area: Area?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("code")
    @Expose
    var code: String?,
    @SerializedName("plan")
    @Expose
    var plan: String?,
    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String?
)

data class Squad(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("position")
    @Expose
    var position: String?,
    @SerializedName("dateOfBirth")
    @Expose
    var dateOfBirth: String?,
    @SerializedName("countryOfBirth")
    @Expose
    var countryOfBirth: String?,
    @SerializedName("nationality")
    @Expose
    var nationality: String?,
    @SerializedName("shirtNumber")
    @Expose
    var shirtNumber: Int?,
    @SerializedName("role")
    @Expose
    var role: String?
)
