package com.learning.lexidictionary.apiService

import com.learning.lexidictionary.model.learnerEdition.LearnerData
import com.learning.lexidictionary.model.studentEdition.StudentData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DictionaryService{
    //LearnerEditon
    @GET("learners/json/{query}")
    fun getLearnerResult (
        @Path("query") query: String,
        @Query("key") key : String = "Enter Your API Keys"
    ) : Call<LearnerData>

    //StudentEdition
    @GET("/sd4/json/{query}")
    fun getStudentResult (
        @Path("query") query : String,
        @Query("key") key : String = "Enter Your API Keys"
    ) : Call<StudentData>
}