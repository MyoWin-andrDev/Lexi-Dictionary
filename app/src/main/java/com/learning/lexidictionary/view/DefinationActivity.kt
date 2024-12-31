package com.learning.lexidictionary.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learning.lexidictionary.R
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.definition.WordDefinition
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefinationActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityDefinationBinding
    private lateinit var wordId : String
    private val url : String = "https://od-api-sandbox.oxforddictionaries.com/api/v2/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_defination)
        wordId = intent.getStringExtra("id").toString()
    }

    fun loadDefinition(wordId : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.getDefinition(wordId)
        call.enqueue(object : Callback<WordDefinition> {
            override fun onResponse(
                call: Call<WordDefinition>,
                response: Response<WordDefinition>
            ) {
                //TODO("Not yet implemented")
                if(response.isSuccessful){
                    val result = response.body()!!.results
                    val definition = result[0].lexicalEntries[0].entries[0].senses[0].definitions[0]
                    val phrases = result[0].lexicalEntries[0].phrases
                    Log.d("definition", definition)
                    phrases.forEach { index ->
                        Log.d("$index", index.toString())
                    }
                }
            }

            override fun onFailure(call: Call<WordDefinition>, t: Throwable) {
                // TODO("Not yet implemented")
            }

        })
    }
}