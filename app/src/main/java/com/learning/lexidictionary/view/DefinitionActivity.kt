package com.learning.lexidictionary.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.learnerEdition.LearnerData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DefinitionActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityDefinationBinding
    private lateinit var wordId : String
    private lateinit var learnerData: List<LearnerData>
    private val url : String = "https://dictionaryapi.com/api/v3/references/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDefinationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordId = intent.getStringExtra("id").toString()
        Log.d("wordId",wordId)
        binding.definitionRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadResultList(wordId)
    }

    private fun loadResultList(wordId : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.getLearnerResult(wordId)
        call.enqueue(object : Callback<LearnerData> {
            @RequiresApi(35)
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<LearnerData>, response: Response<LearnerData>){
                //TODO("Not yet implemented")
                if(response.isSuccessful) {
                    val learnerData = listOf(response.body()!!)
                   // Log.d("learnerData", learnerData.toString())
                    val result = response.body()!![0]
                   // Log.d("result", result.toString())
                    val learnerList = listOf(response.body()!![0])
                   // Log.d("learnerList", learnerList.toString())
                    //WordText
                    binding.wordText.text = result.meta.id
                    //Phonetic
                    val pronunciation = result.hwi.prs
                    val phonetic = pronunciation!![0].ipa
                    binding.phonetic.text = "/$phonetic/"
                    //Definition
                    val definition = result.meta.appShortDef.def
                    val replacedDefList = replaceBC(definition!!)
                    Log.d("list", replacedDefList.toString())
                    binding.definitionRecycler.adapter = DefinitionAdapter(this@DefinitionActivity, learnerData, wordId)
                   // val largest = getItemSize(wordId, listOf(result))
//                        when (definition.size) {
//                            1 -> binding.definitionText1.text = replacedDefList[0]
//                            2 -> {
//                                binding.definitionText1.text = replacedDefList[0]
//                                binding.definitionText2.isVisible = true
//                                binding.definitionText2.text = replacedDefList[1]
//                            }
//
//                            in 3..10 -> {
//                                binding.definitionText1.text = replacedDefList[0]
//                                binding.definitionText2.isVisible = true
//                                binding.definitionText2.text = replacedDefList[1]
//                                binding.definitionText3.isVisible = true
//                                binding.definitionText3.text = replacedDefList[2]
//                        }
//                    }
                }
            }

            override fun onFailure(call: Call<LearnerData>, t: Throwable) {
                // TODO("Not yet implemented")
            }

        })
    }

    private fun replaceBC( list : List<String>) : List<String> {
         val replacedList = list.map { list ->
            val occurrence = list.split("{bc}").size - 1
             when (occurrence) {
                 0 -> list
                 1 -> list.replace("{bc}", "\u2022 ")
                 else -> list.replaceFirst("{bc}","\u2022").replaceFirst("{bc}","\n\u2022")
             }
        }
        Log.d("replace", replacedList.toString())
        return replacedList
    }

}