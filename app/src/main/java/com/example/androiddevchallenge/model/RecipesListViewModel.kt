package com.example.androiddevchallenge.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RecipesListViewModel : ViewModel() {

    private val TAG: String = RecipesListViewModel::class.java.simpleName

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun generateAndAddANewRecipe() {
        val value = mutableListOf<Recipe>()
        _recipes.value?.apply {
            value.addAll(this)
        }
        value.add(RecipesDataGenerator.generateRecipes(1).first())
        _recipes.value = value
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }

}