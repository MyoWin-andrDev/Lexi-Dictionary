package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.definition.WordDefinition
import com.learning.lexidictionary.model.search.WordSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryService{
    @GET("search/en-gb")
    fun searchWord(
        @Query("q") entry : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "819d7d21",
        @Header("app_key") key : String = "4e4d038084d005f2ea4ef0f617e921d0"
    ) : Call<WordSearch>

    @GET("words/en-gb")
    fun getDefinition(
        @Query("q") search : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "819d7d21",
        @Header("app_key") key : String = "4e4d038084d005f2ea4ef0f617e921d0"
    ) : Call<WordDefinition>
}