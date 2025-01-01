package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.entries.EntriesData
import com.learning.lexidictionary.model.entries.Result
import com.learning.lexidictionary.model.word.WordData
import com.learning.lexidictionary.model.search.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryService{
    @GET("search/en-gb")
    fun search(
        @Query("q") entry : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "819d7d21",
        @Header("app_key") key : String = "4e4d038084d005f2ea4ef0f617e921d0"
    ) : Call<SearchData>

    @GET("words/en-gb")
    fun word(
        @Query("q") search : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "819d7d21",
        @Header("app_key") key : String = "4e4d038084d005f2ea4ef0f617e921d0"
    ) : Call<WordData>

    @GET("entries/en-gb/{word}")
    fun entries (
        @Path("word") word : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "819d7d21",
        @Header("app_key") key : String = "4e4d038084d005f2ea4ef0f617e921d0",
    ) : Call<EntriesData>
}