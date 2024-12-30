package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.DefinationData
import com.learning.lexidictionary.model.DefinationDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryAPI{
    @GET("sd4/json/{word}")
    fun getEntry(
        @Path("word") word : String,
        @Query("key") key : String = "d6b440b8-391a-44b8-8889-c080bc87d68a"
    ) : Call<DefinationData>
}