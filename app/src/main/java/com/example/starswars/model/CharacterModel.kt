package com.example.starswars.model

data class CharacterModel(val name: String, val height: Int, val mass: Int,
                          val hair_color: String, val skin_color: String, val eye_color: String,
                          val birth_year: String, val gender: String, val homeworld: String,
                          val films: ArrayList<String>, val species: ArrayList<String>, val vehicles: ArrayList<String>,
                          val starships: ArrayList<String>, val created: String, val edited: String, val url: String)