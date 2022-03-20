package com.example.myapplication.groupsPhones

import androidx.compose.runtime.mutableStateListOf

object RepoGPhone{
    val mList = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    fun refreshMList(){
        mList.clear()
        mList.addAll(listOf<GPhone>(
            GPhone("DP-SAFI","MARRAKECH-SAFI","", official = false, fav = false),
            GPhone("DP-KALAA-SRAGHNA","MARRAKECH-SAFI","", official = false, fav = false)
        ))
        mListCats.clear()
        mListCats.addAll(listOf("un","deux","trois","cinq"))
    }
}