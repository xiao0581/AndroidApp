package com.example.obopgave.Repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.obopgave.ViewModel.Beer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class BeerRepository {

    private val baseUrl = "https://anbo-restbeer.azurewebsites.net/api/"
    // the specific (collection) part of the URL is on the individual methods in the interface beerService

    private val beerService: BeerService
    val BeersFlow: MutableState<List<Beer>> = mutableStateOf(listOf())
    val isLoadingBeers = mutableStateOf(false)
    val errorMessageFlow = mutableStateOf("")

    init {
        //val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // GSON
            //.addConverterFactory(KotlinJsonAdapterFactory)
            //.addConverterFactory(MoshiConverterFactory.create(moshi)) // Moshi, added to Gradle dependencies
            .build()
        beerService = build.create(BeerService::class.java)
        getBeers()
    }

    fun getBeers() {
        isLoadingBeers.value = true
        beerService.getAllBeers().enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                isLoadingBeers.value = false
                if (response.isSuccessful) {
                    //Log.d("APPLE", response.body().toString())
                    val beerList: List<Beer>? = response.body()
                    BeersFlow.value = beerList ?: emptyList()
                    errorMessageFlow.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                isLoadingBeers.value = false
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message
                Log.d("APPLE", message)
            }
        })
    }

    fun add(Beer: Beer) {
        beerService.saveBeer(Beer).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Added: " + response.body())
                    getBeers()
                    errorMessageFlow.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message
                Log.d("APPLE", message)
            }
        })
    }

    fun delete(id: Int) {
        Log.d("APPLE", "Delete: $id")
        beerService.deleteBeer(id).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Delete: " + response.body())
                    errorMessageFlow.value = ""
                    getBeers()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message
                    Log.d("APPLE", "Not deleted: $message")
                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message
                Log.d("APPLE", "Not deleted $message")
            }
        })
    }

    fun update(BeerId: Int, Beer: Beer) {
        Log.d("APPLE", "Update: $BeerId $Beer")
        beerService.updateBeers(BeerId, Beer).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Updated: " + response.body())
                    errorMessageFlow.value = ""
                    Log.d("APPLE", "update successful")
                    getBeers()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message
                    Log.d("APPLE", "Update $message")
                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message
                Log.d("APPLE", "Update $message")
            }
        })
    }


    }
