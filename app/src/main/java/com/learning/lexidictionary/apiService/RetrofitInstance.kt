package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.search.Result
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val url : String = "https://od-api-sandbox.oxforddictionaries.com/api/v2/"
    private lateinit var resultList : List<Result>
    fun loadWord(word : String)  : DictionaryService{
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(DictionaryService::class.java)
        return apiService
    }
}