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
        @Query("key") key : String = "636fd0e7-95e2-476a-8495-eee2f107cf16"
    ) : Call<LearnerData>

    //StudentEdition
    @GET("/sd4/json/{query}")
    fun getStudentResult (
        @Path("query") query : String,
        @Query("key") key : String = "d6b440b8-391a-44b8-8889-c080bc87d68a"
    ) : Call<StudentData>

    //Thesaurus
    @GET("thesaurus")
    fun getThesaurusResult(
        @Query("word") word : String,
        @Header("x-api-key") api : String = "3rQJb6U2OTs6SyYCSvq+Mg==7lhwFHVuH52NjQP6"
    ) : Call<Thesaurus>
}