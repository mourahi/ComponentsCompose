package com.example.myapplication.formation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoFormation{
    private const val linkToAllFormation = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    val mList = mutableStateListOf<Formation>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(){
       /* val d = listOf(
            Formation("un titre","un contenu ici"),
            Formation("un deuxieme titre","un deuxieme contenu")
        )*/
        val d = groupsPhoneFromServer()
            val cat = d.map{it.cat}.toSet()
            Log.d("adil","catFormation = ${cat.toList()}")
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)


    }

    // en cours de préparation
    private suspend fun groupsPhoneFromServer(): List<Formation> {
        val a = ApiSheet.request(id = linkToAllFormation, "formation")
        Log.d("adil","a=$a")
        val re = mutableListOf<Formation>()
        if (a.isNotEmpty()) {
            repeat(a.size) {
                    val d = a[it]
                    re.add(Formation(name = d[0], contenu = d[1], image = d[2],"","","",cat = d[6]))
            }
        }
        return re
    }


    // fin
}