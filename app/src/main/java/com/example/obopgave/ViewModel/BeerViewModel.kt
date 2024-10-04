package com.example.obopgave.ViewModel

import androidx.lifecycle.ViewModel
import com.example.obopgave.Repository.BeerRepository
import androidx.compose.runtime.State
class BeerViewModel:ViewModel() {
    private val repository = BeerRepository()
    val BeersFlow: State<List<Beer>> = repository.BeersFlow
    val errorMessageFlow: State<String> = repository.errorMessageFlow

    // TODO use reloadingFlow to show loading indicator
    val reloadingFlow: State<Boolean> = repository.isLoadingBeers

    init {
        reload()
    }

    fun reload() {
        repository.getBeers()
    }

    fun add(Beer: Beer) {
        repository.add(Beer)
    }

    fun update(BeerId: Int, Beer: Beer) {
        repository.update(BeerId, Beer)
    }

    fun remove(Beer: Beer) {
        repository.delete(Beer.id)


    }
    fun sortBooksByName(ascending: Boolean) {
        repository.sortBooksByName(ascending)
    }

    fun sortBooksByAbv(ascending: Boolean) {
        repository.sortBooksByAbv(ascending)
    }

    fun filterByName(titleFragment: String) {
        repository.filterByTName(titleFragment)
    }
    fun filterByAbv(minAbv: Double) {
        repository.filterByAbv(minAbv)
    }
    fun filterByNameAndAbv(nameFragment: String, maxAbv: Double) {
        repository.filterByNameAndAbv(nameFragment, maxAbv)
    }

}