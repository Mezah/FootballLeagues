package com.example.footballleagues.repository.remote

import com.example.footballleagues.BuildConfig
import com.example.footballleagues.R
import com.example.footballleagues.data.api.LeaguesResponse
import com.example.footballleagues.data.api.TeamResponse
import com.example.footballleagues.data.api.TeamsResponse
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    fun getTierOneLeaguesList(): Single<LeaguesResponse> {
        return endPoint.getLeaguesList()
            .subscribeOn(Schedulers.io())
    }

    fun getTeamsListInLeagues(leagueId: String): Single<TeamsResponse> {
        return endPoint.getTeamsListInLeague(
            BuildConfig.API_KEY,
            leagueId
        )
    }

    fun getTeamInformation(teamId: String): Single<TeamResponse> {
        return endPoint.getTeamInformation(
            BuildConfig.API_KEY,
            teamId
        )
    }
}

sealed class Result<out R> {
    object Loading : Result<Nothing>()
    data class Success(val data: R) : Result<R>()
    data class Error(val error: Exception) : Result<Nothing>()
}