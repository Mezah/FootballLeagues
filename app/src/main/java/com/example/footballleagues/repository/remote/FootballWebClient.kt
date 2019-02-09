package com.example.footballleagues.repository.remote

import com.example.footballleagues.BuildConfig
import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.data.api.TeamResponse
import com.example.footballleagues.data.api.TeamsResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Simple layer between End point and consumer
 */
class FootballWebClient private constructor() {
    private val endPoint: FootballAPIEndPoint

    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun createRetrofitClient(): FootballAPIEndPoint {
        val okhttpClient = createOkHttpClient()
        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        val gson = gsonBuilder
            .create()
        val builder = Retrofit.Builder()

        builder.baseUrl(BuildConfig.BASE_URL)
            .client(okhttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return builder.build().create(FootballAPIEndPoint::class.java)
    }

    init {
        // create retrofit client
        endPoint = createRetrofitClient()
    }

    companion object {
        @Volatile
        private var INSTANCE: FootballWebClient? = null

        fun getMyStoreServices(): FootballWebClient {
            return INSTANCE ?: synchronized(FootballWebClient::class.java) {
                return INSTANCE ?: FootballWebClient().also {
                    INSTANCE = it
                }
            }
        }

    }

    fun getTierOneLeaguesList(): Deferred<Response<LeaguesResponse>> {
        return endPoint.getLeaguesList()
    }

    fun getTeamsListInLeagues(leagueId: String): Deferred<Response<TeamsResponse>> {
        return endPoint.getTeamsListInLeague(
            BuildConfig.API_KEY,
            leagueId
        )
    }

    fun getTeamInformation(teamId: String): Deferred<Response<TeamResponse>> {
        return endPoint.getTeamInformation(
            BuildConfig.API_KEY,
            teamId
        )
    }
}