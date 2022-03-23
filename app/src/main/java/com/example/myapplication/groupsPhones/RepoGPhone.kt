package com.example.myapplication.groupsPhones

import androidx.compose.runtime.mutableStateListOf

object RepoGPhone{
    val mList = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    fun refreshMList(){
        val d = listOf<GPhone>(
            GPhone("DP-SAFI","MARRAKECH-SAFI","", official = false, fav = false),
            GPhone("DP-KALAA-SRAGHNA","MARRAKECH-SAFI","", official = false, fav = false)
        )
        val cat = d.map{it.cat}.toSet()
        mList.clear()
        mList.addAll(d)
        mListCats.clear()
        mListCats.addAll(cat)
    }
}