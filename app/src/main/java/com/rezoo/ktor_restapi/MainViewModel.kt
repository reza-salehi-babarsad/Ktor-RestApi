package com.rezoo.ktor_restapi

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezoo.ktor_restapi.data.Cat
import com.rezoo.ktor_restapi.data.CatsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     private val api: CatsApi
): ViewModel(){

    private val _state = mutableStateOf(CatState())
    val state: State<CatState> = _state
    init {
        getRandomCat()
    }

    fun getRandomCat(){
        viewModelScope.launch{
            try {
                _state.value=state.value.copy(isLoading = true)
                _state.value=state.value.copy(
                    cat = api.getRandomCat(),
                    isLoading = false
                )
            }catch (e:Exception){
                Log.e("MainViewModel","getRandomCat:",e)
                _state.value=state.value.copy(isLoading = false)
            }
        }
    }

    data class CatState(
        val cat: Cat? = null,
        val isLoading :Boolean= false
    )
}