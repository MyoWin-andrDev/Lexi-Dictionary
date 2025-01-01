package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.entry.EntriesData
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
        @Header("app_id") id : String = "fb1422d6",
        @Header("app_key") key : String = "ec242b760218be5712c78c2028067dc2"
    ) : Call<SearchData>

    @GET("words/en-gb")
    fun word(
        @Query("q") search : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "fb1422d6",
        @Header("app_key") key : String = "ec242b760218be5712c78c2028067dc2"
    ) : Call<WordData>

    @GET("entries/en-gb/{word}")
    fun entries (
        @Path("word") word : String,
        @Header("Accept") accept : String = "application/json",
        @Header("app_id") id : String = "fb1422d6",
        @Header("app_key") key : String = "ec242b760218be5712c78c2028067dc2",
    ) : Call<EntriesData>
}