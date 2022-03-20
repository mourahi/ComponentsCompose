package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.components.CToggle
import kotlinx.coroutines.launch

class VmGPh:ViewModel() {
    private val mListInitial = mutableListOf<GPhone>()
    val mList = RepoGPhone.mList

    val mListCats = mutableStateListOf<String>()
    val mListCatsSelected = mutableListOf("")
    init {
        viewModelScope.launch {
            RepoGPhone.refreshMList()
            mListInitial.clear() ; mListInitial.addAll(mList)
            mListCats.clear();mListCats.addAll(RepoGPhone.mListCats)
        }
    }



    // debut Icons Card
    val listToggle = listOf(
        CToggle(icon1 = Icons.Filled.FavoriteBorder,
            icon2 = Icons.Filled.Favorite,
            selected = false,
            txt = "12"){ Log.d("adil","I am here 1 click = $it")},
        CToggle(
            icon1 = Icons.Filled.Add,
            icon2 = Icons.Filled.Delete,
            selected =  false,
            txt = null){ Log.d("adil","I am here 2 click = $it")},
    )

    val listToggle3 = listOf(
        CToggle(
            icon1 = Icons.Filled.Check,
            icon2 = Icons.Filled.CheckCircle,
            selected = false,
            txt =  null){ Log.d("adil","I am here 1 click = $it")})
    //fin
}