package com.example.androiddevchallenge.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RecipesListViewModel : ViewModel(), OnDeleteRecipe {

    private val TAG: String = RecipesListViewModel::class.java.simpleName

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _recipesToDelete = MutableLiveData<List<Recipe>>()
    val recipesToDelete: LiveData<List<Recipe>> = _recipesToDelete

    fun generateAndAddANewRecipe() {
        _recipes.invoke {
            it.add(RecipesDataGenerator.generateRecipes(1).first())
        }
    }

    override fun prepareForDeletion(recipe: Recipe) {
        _recipesToDelete.invoke {
            if (!it.contains(recipe)) {
                it.add(recipe)
            }
        }
    }

    override fun confirmForDeletion(recipe: Recipe) {
        _recipes.invoke {
            it.remove(recipe)
        }
        _recipesToDelete.invoke {
            it.remove(recipe)
        }
    }

    override fun cancelDeletion(recipe: Recipe) {
        _recipesToDelete.invoke {
            it.remove(recipe)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }

    /**
     * https://developer.android.com/jetpack/compose/state#use-other-types-of-state-in-jetpack-compose
     *
     * Mutable objects that are not observable, such as ArrayList<T> or a mutable data class,
     * cannot be observed by Compose to trigger recomposition when they change.
     */
    private fun MutableLiveData<List<Recipe>>.invoke(doAction: (MutableList<Recipe>) -> Unit) {
        val value = mutableListOf<Recipe>()
        this.value?.apply {
            value.addAll(this)
        }
        doAction.invoke(value)
        this.value = value
    }
}

interface OnDeleteRecipe {
    fun prepareForDeletion(recipe: Recipe)
    fun confirmForDeletion(recipe: Recipe)
    fun cancelDeletion(recipe: Recipe)
}