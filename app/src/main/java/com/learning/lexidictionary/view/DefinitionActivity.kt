package com.learning.lexidictionary.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.learning.lexidictionary.adapter.AdditionalAdapter
import com.learning.lexidictionary.adapter.AntonymAdapter
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.adapter.LexicalAdapter
import com.learning.lexidictionary.adapter.SynonymAdapter
import com.learning.lexidictionary.apiService.DictionaryService
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.learnerEdition.LearnerData
import com.learning.lexidictionary.handler.DefinitionHandler
import com.learning.lexidictionary.model.learnerEdition.Meta
import com.learning.lexidictionary.model.thesaurus.Thesaurus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DefinitionActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityDefinationBinding
    private lateinit var wordId : String
    private lateinit var learnerData: List<LearnerData>
    //Lexi Boolean
    private var isLexi = true
    private var isSynonym = true
    private var isAntonym = true
    //Base URL
    private val merriamWebsterUrl : String = "https://dictionaryapi.com/api/v3/references/"
    private val thesaurusUrl : String = "https://api.api-ninjas.com/v1/"
    //LexiList
    private lateinit var lexicalList : List<String>
    private lateinit var synonymList : List<String>
    private lateinit var antonymList : List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDefinationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordId = intent.getStringExtra("id").toString()
        Log.d("wordId",wordId)
        initLayoutManager()
        loadResultList(wordId)
        loadThesaurusResult()
        seeMoreClicks()
    }

    private fun initLayoutManager(){
        binding.definitionRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.additionalRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.lexicalRecycler.layoutManager = StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.GAP_HANDLING_NONE)
        binding.synonymRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_NONE)
        binding.antonymRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_NONE)
    }

    //Merriam-Webster Retrofit
    private fun loadResultList(wordId : String){
        //Creating Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl(merriamWebsterUrl)
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
                    val learnerDataItem = response.body()!![0]
                    val learnerList = listOf(response.body()!![0])
                    //WordText
                    binding.wordText.text = learnerDataItem.meta.id
                    //Phonetic
                    val pronunciation = learnerDataItem.hwi.prs
                    if(pronunciation != null){
                        val phonetic = pronunciation[0].ipa
                        binding.phonetic.text = "/$phonetic/"
                    }
                    //Definition
                    DefinitionHandler(this@DefinitionActivity, wordId).getMainDef(learnerList, binding)
                    binding.definitionRecycler.adapter = DefinitionAdapter(this@DefinitionActivity, learnerList, wordId)
                    binding.additionalRecycler.adapter = AdditionalAdapter(this@DefinitionActivity, learnerList, wordId)
                    lexicalList = learnerDataItem.meta.stems
                    binding.lexicalRecycler.adapter = LexicalAdapter(this@DefinitionActivity, lexicalList)

                }
            }

            override fun onFailure(call: Call<LearnerData>, t: Throwable) {
                // TODO("Not yet implemented")
            }

        })
    }

    //Thesaurus Retrofit
    private fun loadThesaurusResult(){
        val retrofit = Retrofit.Builder()
            .baseUrl(thesaurusUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(DictionaryService::class.java)
        val call = apiService.getThesaurusResult(wordId)
        call.enqueue(object : Callback<Thesaurus>{
            override fun onResponse(call: Call<Thesaurus>, response: Response<Thesaurus>) {
                //TODO("Not yet implemented")
                if(response.isSuccessful){
                    val thesaurusList = response.body()
                    synonymList = thesaurusList!!.synonyms
                    antonymList = thesaurusList.antonyms
                    binding.synonymRecycler.adapter = SynonymAdapter(this@DefinitionActivity, synonymList)
                    binding.antonymRecycler.adapter = AntonymAdapter(this@DefinitionActivity, antonymList)
                }
            }

            override fun onFailure(call: Call<Thesaurus>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    //SeeMore Clicks
    @SuppressLint("SetTextI18n")
    private fun seeMoreClicks(){
        //Lexical Field
        binding.seeMoreLayout3.setOnClickListener{
            if(isLexi) {
                binding.lexicalRecycler.layoutManager =
                    FlexboxLayoutManager(this@DefinitionActivity)
                binding.lexicalRecycler.adapter = LexicalAdapter(this@DefinitionActivity, lexicalList)
                binding.seeMoreMenu3.text = " See Less "
                isLexi = false
            }
            else{
                binding.lexicalRecycler.layoutManager =
                    StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.GAP_HANDLING_NONE)
                binding.lexicalRecycler.adapter = LexicalAdapter(this@DefinitionActivity, lexicalList)
                binding.seeMoreMenu3.text = " See More "
                isLexi = true
            }
        }
        //Synonym Field
        binding.seeMoreLayout4.setOnClickListener {
            if(isSynonym){
                binding.synonymRecycler.layoutManager = FlexboxLayoutManager(this@DefinitionActivity)
                binding.synonymRecycler.adapter = SynonymAdapter(this@DefinitionActivity, synonymList)
                binding.seeMoreMenu4.text = " See Less "
                isSynonym = false
            }
            else {
                binding.synonymRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_NONE)
                binding.synonymRecycler.adapter = SynonymAdapter(this@DefinitionActivity, synonymList)
                binding.seeMoreMenu4.text = " See More "
                isSynonym = true
            }
        }

        //Antonym Field
        binding.seeMoreLayout5.setOnClickListener {
            if(isAntonym){
                binding.antonymRecycler.layoutManager = FlexboxLayoutManager(this@DefinitionActivity)
                binding.antonymRecycler.adapter = SynonymAdapter(this@DefinitionActivity, synonymList)
                binding.seeMoreMenu5.text = " See Less "
                isAntonym = false
            }
            else {
                binding.antonymRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.GAP_HANDLING_NONE)
                binding.antonymRecycler.adapter = SynonymAdapter(this@DefinitionActivity, synonymList)
                binding.seeMoreMenu5.text = " See More "
                isAntonym = true
            }
        }
    }



}