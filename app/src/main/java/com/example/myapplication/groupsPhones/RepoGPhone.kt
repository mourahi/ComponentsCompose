package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoGPhone{
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    val mList = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(){
       /* val d = listOf(
            GPhone("DP-SAFI","MARRAKECH-SAFI","", official = false, fav = false),
            GPhone("DP-KALAA-SRAGHNA","MARRAKECH-SAFI","", official = false, fav = false)
        )*/
        val d = groupsPhoneFromServer()
            val cat = d.map{it.cat}.toSet()
            Log.d("adil","cat = ${cat.toList()}")
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)


    }

    // en cours de préparation
    private suspend fun groupsPhoneFromServer(): List<GPhone> {
        val a = ApiSheet.request(id = linkToAllGPhone, "groupe")
        //Log.d("adil","a=$a")
        val re = mutableListOf<GPhone>()
        if (a.isNotEmpty()) {
            repeat(a.size) {
                val d = a[it]
                re.add(GPhone(d[0], d[1], d[2], true))
            }
        }
        return re
    }


    // fin
}