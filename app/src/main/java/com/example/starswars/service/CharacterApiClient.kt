package com.example.starswars.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterApiClient {
    companion object {

        val base_url = "https://swapi.co/api/"

        fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
        }

        fun getService(): CharacterApiService {
            return getRetrofitInstance().build().create(CharacterApiService::class.java)
        }
    }
}