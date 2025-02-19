package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.learnerEdition.LearnerData
import com.learning.lexidictionary.model.studentEdition.StudentData
import com.learning.lexidictionary.model.thesaurus.Thesaurus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface DictionaryService{
    //LearnerEditon
    @GET("learners/json/{query}")
    fun getLearnerResult (
        @Path("query") query: String,
        @Query("key") key : String = "Your API Key"
    ) : Call<LearnerData>

    //StudentEdition
    @GET("/sd4/json/{query}")
    fun getStudentResult (
        @Path("query") query : String,
        @Query("key") key : String = "Your API Key"
    ) : Call<StudentData>

    //Thesaurus
    @GET("thesaurus")
    fun getThesaurusResult(
        @Query("word") word : String,
        @Header("x-api-key") api : String = "Your API Key"
    ) : Call<Thesaurus>
}