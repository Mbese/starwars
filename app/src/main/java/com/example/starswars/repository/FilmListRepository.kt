package com.example.starswars.repository

import android.arch.lifecycle.MutableLiveData
import com.example.starswars.model.FilmListModel
import com.example.starswars.model.CharacterModel
import com.example.starswars.service.ApiClient
import com.example.starswars.service.CharacterApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmListRepository: IMoviesListRepository {

    override fun getFilmCharacters(liveData: MutableLiveData<CharacterModel>, character: Int?) {
        val apiService = CharacterApiClient.getService()
        apiService.getCharacter(character).enqueue(object : Callback<CharacterModel> {
            override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                liveData.value = null
            }
        })
    }

    override fun getFilmDetails(liveData: MutableLiveData<FilmListModel>, filmNumber: Int?) {
        val apiService = ApiClient.getService()
        apiService.getFilm(filmNumber).enqueue(object : Callback<FilmListModel> {
            override fun onResponse(call: Call<FilmListModel>, response: Response<FilmListModel>) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FilmListModel>, t: Throwable) {
                liveData.value = null
            }
        })
    }

}