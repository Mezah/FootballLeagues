package com.example.footballleagues.data.api

import com.google.gson.annotations.SerializedName


class LeaguesResponse {

    @SerializedName("count")
    var count: Int? = null
    @SerializedName("competitions")
    var competitions: List<Competition?>? = null

}


class Competition {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("area")
    var area: Area? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("code")
    var code: String? = null
    @SerializedName("emblemUrl")
    var emblemUrl: Any? = null
    @SerializedName("plan")
    var plan: String? = null
    @SerializedName("currentSeason")
    var currentSeason: Season? = null
    @SerializedName("numberOfAvailableSeasons")
    var numberOfAvailableSeasons: Int? = null
    @SerializedName("lastUpdated")
    var lastUpdated: String? = null
}

data class Area(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)

data class Season(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("startDate")
    var startDate: String?,
    @SerializedName("endDate")
    var endDate: String?,
    @SerializedName("currentMatchday")
    var currentMatchday: Int?,
    @SerializedName("winner")
    var winner: Winner?
)

data class Winner(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("shortName")
    var shortName: String?,
    @SerializedName("tla")
    var tla: String?,
    @SerializedName("crestUrl")
    var crestUrl: Any?
)
