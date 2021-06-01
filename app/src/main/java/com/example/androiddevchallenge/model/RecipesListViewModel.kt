package com.example.androiddevchallenge.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log


class RecipesListViewModel : ViewModel() {

    private val TAG: String = RecipesListViewModel::class.java.simpleName

    private val _recipes = MutableLiveData<MutableList<Recipe>>()
    val recipes: LiveData<MutableList<Recipe>> = _recipes

    fun generateAndAddANewRecipe() {
        val recipe = RecipesDataGenerator.generateRecipes(1).first()
        _recipes.value?.add(recipe)
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }

}