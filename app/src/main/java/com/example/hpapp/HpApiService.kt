package com.example.hpapp

import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {
    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<CharacterResponse>

    @GET("api/characters/staff")
    suspend fun getHogwartsStaff(): List<CharacterResponse>

    @GET("api/characters/house/{house}")
    suspend fun getCharactersByHouse(@Path("house") house: String): List<CharacterResponse>
}
