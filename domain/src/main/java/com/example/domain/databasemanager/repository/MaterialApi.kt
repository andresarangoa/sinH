package com.example.domain.databasemanager.repository

import com.example.domain.databasemanager.entities.WordEntityRemote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MaterialApi {
    companion object {
        private const val WORDS = "words"
        private const val WORDS_BY_LETTER = "$WORDS"
    }

    @GET(WORDS)
    fun words(): Call<List<WordEntityRemote>>

    @GET(WORDS_BY_LETTER)
    fun wordsByLetter(@Query("letter") letter :String): Call<List<WordEntityRemote>>
}