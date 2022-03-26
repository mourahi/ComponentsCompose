package com.example.myapplication.phones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoPhone{
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    val mList = mutableStateListOf<Phone>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(){
        /*val d = listOf(
            Phone("DP-SAFI","MARRAKECH-SAFI","",  fav = false),
            Phone("DP-KALAA-SRAGHNA","MARRAKECH-SAFI","",fav = false)
        )*/
        val d = groupsPhoneFromServer()
            val cat = d.map{it.cat}.filter { it.isNotEmpty() }.toSet()
            Log.d("adil","cat = ${cat.toList()}")
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)


    }

    // en cours de préparation
    /*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */
    private suspend fun groupsPhoneFromServer(): List<Phone> {
        val a = ApiSheet.request(id = linkToAllGPhone, "data")
        //Log.d("adil","a=$a")
        val re = mutableListOf<Phone>()
        if (a.isNotEmpty()) {
            repeat(a.size) {
                if(it>0){
                    val d = a[it]
                    re.add(Phone(cat = d[0], name = d[3], person =  d[4]))
                }

            }
        }
        return re
    }


    // fin
}