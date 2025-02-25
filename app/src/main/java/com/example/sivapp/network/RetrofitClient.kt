package com.example.sivapp.network

import com.example.sivapp.network.response.WebService
import com.example.sivapp.utils.Constantes
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
 val webService: WebService by lazy {
     Retrofit.Builder()
         .baseUrl(Constantes.BASE_URL)
         .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
         .build().create(WebService::class.java)

 }
}