package com.example.networkingwithretrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RetrofitService {
    @GET("movie/{id}")
    fun getMovieForId(@Path("id") id: Int, @Query("api_key") api_key: String ): Deferred<MovieItem>


    companion object {
        fun create(baseUrl: String): RetrofitService {

            val retrofit = Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()))
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}
