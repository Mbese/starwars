package com.example.starswars.service

import com.example.starswars.model.CharacterModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {
    @GET("people/{character}/")
    fun getCharacter(@Path("character") character: Int?): Call<CharacterModel>
}