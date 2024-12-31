package com.learning.lexidictionary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.gson.Gson
import com.learning.lexidictionary.adapter.SuggestionList
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityMainBinding
import com.learning.lexidictionary.model.definition.WordDefinition
import com.learning.lexidictionary.model.search.Result
import com.learning.lexidictionary.model.search.WordSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var resultList : List<Result>
    private lateinit var word : String
    private val url : String = "https://od-api-sandbox.oxforddictionaries.com/api/v2/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayoutManager()
        initListener();
    }

    private fun initListener(){
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              //  TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               word =  binding.searchBar.text?.trim().toString()
                loadWord(word)
            }

            override fun afterTextChanged(p0: Editable?) {
               // TODO("Not yet implemented")
            }

        })
    }
    private fun initLayoutManager() {
       // TODO("Not yet implemented")
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun loadWord(word : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.searchWord(word)
        call.enqueue(object : Callback<WordSearch> {
            override fun onResponse(call: Call<WordSearch>, response: Response<WordSearch>) {
              //  TODO("Not yet implemented")
                if(response.isSuccessful){
                    resultList = response.body()!!.results
                    Log.d("result",resultList.toString())
                    binding.recyclerView.adapter = SuggestionList(this@MainActivity, resultList, word)
                }
            }
            override fun onFailure(call: Call<WordSearch>, t: Throwable) {
               // TODO("Not yet implemented")
                Log.d("Error", "${t.message}")
            }

        })
    }

    fun loadDefinition(){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.getDefinition("apple")
        call.enqueue(object : Callback<WordDefinition>{
            override fun onResponse(
                call: Call<WordDefinition>,
                response: Response<WordDefinition>
            ) {
                //TODO("Not yet implemented")
                if(response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<WordDefinition>, t: Throwable) {
               // TODO("Not yet implemented")
            }

        })
    }

}