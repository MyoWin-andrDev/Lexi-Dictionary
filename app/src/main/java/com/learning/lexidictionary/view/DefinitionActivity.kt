package com.learning.lexidictionary.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.entry.EntriesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefinitionActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityDefinationBinding
    private lateinit var wordId : String
    private val url : String = "https://od-api-sandbox.oxforddictionaries.com/api/v2/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDefinationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordId = intent.getCharSequenceExtra("id").toString()
        Log.d("wordId",wordId)
        binding.definitionRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadDefinition(wordId)

    }

    fun loadDefinition(wordId : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.entries(wordId)
        call.enqueue(object : Callback<EntriesData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<EntriesData>,
                response: Response<EntriesData>
            ) {
                //TODO("Not yet implemented")
                if(response.isSuccessful) {
                    val result = response.body()?.results
                    if (!result.isNullOrEmpty()) {
                        Log.d("result", result.toString())

                        //WordText
                        val wordText = result[0].word
                        if(wordText.isNotEmpty()){
                                binding.wordText.text = wordText
                            }
                        //Phonetic
                        val phonetic = result[0].lexicalEntries[0].entries[0].pronunciations[0].phoneticSpelling
                        if(phonetic.isNotEmpty()){
                            binding.phonetic.text = "/$phonetic/"
                        }
                        //Definition
                        val definition = result[0].lexicalEntries[0].entries[0].senses[0].definitions[0]
                        if(definition.isNotEmpty()){
                            binding.definitionText.text = result[0].lexicalEntries[0].entries[0].senses[0].definitions[0]
                        }
                        val phrases = result[0].lexicalEntries[0].phrases
                        binding.definitionRecycler.adapter = DefinitionAdapter(this@DefinitionActivity, result, wordId)

                        Log.d("definition", definition)
                        phrases.forEach { index ->
                            Log.d("$index", index.toString())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<EntriesData>, t: Throwable) {
                // TODO("Not yet implemented")
            }

        })
    }
}