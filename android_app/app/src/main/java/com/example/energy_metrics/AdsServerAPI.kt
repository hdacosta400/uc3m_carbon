package com.example.energy_metrics

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AdsServerAPI {

    @GET("get") // endpoint URL for GET
    fun getAds(): Call<List<Ad>>

    @POST("post")
    fun postEnergyDeltas(@Body energyDeltas: EnergyDeltas?): Call<EnergyDeltas?>?
}