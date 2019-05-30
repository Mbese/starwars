package com.example.starswars.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.starswars.model.CharacterModel
import com.example.starswars.repository.FilmListRepository

class FilmCharacterViewModel : ViewModel() {
    val liveData = MutableLiveData <CharacterModel>()
    val repo = FilmListRepository()

    fun getCharacter(character: Int?){
        repo.getFilmCharacters(liveData, character)
    }
}