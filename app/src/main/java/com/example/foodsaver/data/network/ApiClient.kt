package com.example.foodsaver.data.network

import com.example.foodsaver.data.network.api.NotificationApi
import com.example.foodsaver.data.network.api.ProductApi
import com.example.foodsaver.data.network.api.UserApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:8080/" // Para emulador Android
    // Para dispositivo real, usa: "http://TU_IP_LOCAL:8080/"

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    // APIs
    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val productApi: ProductApi = retrofit.create(ProductApi::class.java)
    val notificationApi: NotificationApi = retrofit.create(NotificationApi::class.java)
}