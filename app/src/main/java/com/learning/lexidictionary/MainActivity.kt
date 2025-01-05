package com.learning.lexidictionary

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.JsonReader
import android.util.Log
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learning.lexidictionary.adapter.SuggestionAdapter
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityMainBinding
import com.learning.lexidictionary.model.learnerEdition.Details
import com.learning.lexidictionary.model.learnerEdition.LearnerData
import com.learning.lexidictionary.model.learnerEdition.Null
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var learnerDataList : List<LearnerData>
    private lateinit var word : String
    private val url : String = "https://dictionaryapi.com/api/v3/references/"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        initLayoutManager()
        initListener();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    private fun initListener(){
        val searchBar = binding.searchBar
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              //  TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               word =  searchBar.text?.trim().toString()
                loadResultList(word)
            }

            override fun afterTextChanged(p0: Editable?) {
               // TODO("Not yet implemented")
            }

        })
        // Clear_Icon
        searchBar.setOnTouchListener { _, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN) {
                if (motionEvent.rawX >= (searchBar.right -
                            searchBar.compoundDrawables[2].bounds.width())
                ) {
                    searchBar.text!!.clear()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
    private fun initLayoutManager() {
       // TODO("Not yet implemented")
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun loadResultList(word : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.getLearnerResult(word)
        call.enqueue(object : Callback<LearnerData> {
            @RequiresApi(35)
            override fun onResponse(call: Call<LearnerData>, response: Response<LearnerData>) {
              //  TODO("Not yet implemented")
                if(response.isSuccessful){
                    val learnerData = listOf(response.body()!!)
                    Log.d("learnerData", learnerData.toString())
                    val resultList  = response.body()!![0]
                    Log.d("resultList", resultList.toString())
                    //Related Word List
                    val stemList = resultList.meta.stems
                    Log.d("stemList", stemList.toString())
                    val getItemSize = getItemSize(word, learnerData)
                    binding.recyclerView.adapter = SuggestionAdapter(this@MainActivity, stemList, word)
                }
            }
            override fun onFailure(call: Call<LearnerData>, t: Throwable) {
               // TODO("Not yet implemented")
                Log.d("Error", "${t.message}")
            }

        })
    }

    @RequiresApi(35)
    private fun getItemSize(wordId: String, resultList : List<LearnerData>) : Int {
        val largestNumber : Int = 0
        val dice = resultList.indices
        Log.d("dice", dice.toString())
        for(i in resultList.indices) {
            for (j in resultList.indices) {
                if (resultList[i][j].meta.id.contains(wordId)) {
                    if (resultList[i][j].meta.id.contains(":")) {
                        val count = resultList[i][j].meta.id.substringAfter(":").toInt()
                        Log.d("count", count.toString())
                    }
                }
            }
        }
        // Log.d("largest", largestNumber.toString())
        return largestNumber
    }

}