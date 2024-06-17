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

    @GET("LED")
    suspend fun ledBrightness(@Query("brightness") brightness:String ):EspResponse

    @GET("LCD")
    suspend fun LCD(@Query("message") message:String ):EspResponse
}

object API {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private var retrofit: Retrofit? = null

    // Method to configure Retrofit with a provided base URL
    fun configureRetrofit(baseUrl: String) {
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    // Lazy initialization of retrofitService, reconfigured when baseUrl changes
    val retrofitService: ESPService by lazy {
        retrofit ?: throw IllegalStateException("Retrofit not configured. Call configureRetrofit(baseUrl) first.")
        retrofit!!.create(ESPService::class.java)
    }
}
