package com.example.socios.util

import com.example.socios.service.ApiServicio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ApiServicio by lazy {
        Retrofit.Builder()
            .baseUrl("https://programadormaldito.cl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServicio::class.java)

    }
}

