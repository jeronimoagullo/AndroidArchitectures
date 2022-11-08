package com.jeroagullo.androidarchitectures.model

import android.util.Log
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    val BASE_URL: String = "https://restcountries.com/v2/"

    lateinit var api: CountriesApi

    init{
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(CountriesApi::class.java)
    }

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}