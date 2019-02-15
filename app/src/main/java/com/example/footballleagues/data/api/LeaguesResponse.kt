package com.example.footballleagues.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LeaguesResponse {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("competitions")
    @Expose
    var competitions: List<Competition>? = null

}

class Competition {
    @SerializedName("id")
    @Expose
    var id: Int = -1

    @SerializedName("area")
    @Expose
    var area: Area? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("code")
    var code: String? = null

    @Expose
    @SerializedName("emblemUrl")
    var emblemUrl: String? = null

    @Expose
    @SerializedName("plan")
    var plan: String? = null

    @Expose
    @SerializedName("currentSeason")
    var currentSeason: Season? = null

    @Expose
    @SerializedName("numberOfAvailableSeasons")
    var numberOfAvailableSeasons: Int? = null

    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String? = null
}

data class Area(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("name")
    @Expose
    var name: String?
)

data class Season(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("startDate")
    @Expose
    var startDate: String?,
    @SerializedName("endDate")
    @Expose
    var endDate: String?,
    @SerializedName("currentMatchday")
    @Expose
    var currentMatchday: Int?,
    @SerializedName("winner")
    @Expose
    var winner: Winner?
)

data class Winner(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("shortName")
    @Expose
    var shortName: String?,
    @SerializedName("tla")
    @Expose
    var tla: String?,
    @SerializedName("crestUrl")
    @Expose
    var crestUrl: Any?
)
