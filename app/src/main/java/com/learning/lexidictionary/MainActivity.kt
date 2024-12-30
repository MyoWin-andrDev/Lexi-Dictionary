package com.learning.lexidictionary

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learning.lexidictionary.apiService.DictionaryAPI
import com.learning.lexidictionary.databinding.ActivityMainBinding
import com.learning.lexidictionary.model.DefinationData
import com.learning.lexidictionary.model.DefinationDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LoadDefination()
    }

    fun LoadDefination(){
        //Creating Retrofit Instance
        val retrofit  = Retrofit.Builder()
            .baseUrl("https://dictionaryapi.com/api/v3/references/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryAPI::class.java)
        val call = apiService.getEntry("apple")
        call.enqueue(object : Callback<DefinationData>{
            override fun onResponse(
                call: Call<DefinationData>,
                response: Response<DefinationData>
            ) {
               // TODO("Not yet implemented")
                if(response.isSuccessful){
                    val word = response.body()
                    Log.d("word",word.toString())
                }
            }

            override fun onFailure(call: Call<DefinationData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}