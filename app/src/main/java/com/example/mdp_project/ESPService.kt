package com.example.mdp_project


import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
data class EspResponse(val message:String)

interface ESPService {
    @GET("nyala")
    suspend fun ledOn():EspResponse

    @GET("mati")
    suspend fun ledOff():EspResponse

    @GET("blink")
    suspend fun ledBlink(@Query("n") times:Int):EspResponse
}

object API{
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        // 192.168.43.<2-254>
        .baseUrl("http://10.10.2.127")
        .build()
    val retrofitService:ESPService by lazy {
        retrofit.create(ESPService::class.java)
    }
}