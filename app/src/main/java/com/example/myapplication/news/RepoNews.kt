package com.example.myapplication.news

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoNews{
    private const val linkToAllNews = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    val mList = mutableStateListOf<News>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(){
       /* val d = listOf(
            News("un titre","un contenu ici"),
            News("un deuxieme titre","un deuxieme contenu")
        )*/
        val d = groupsPhoneFromServer()
            val cat = d.map{it.cat}.toSet()
            Log.d("adil","catNews = ${cat.toList()}")
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)


    }

    // en cours de préparation
    private suspend fun groupsPhoneFromServer(): List<News> {
        val a = ApiSheet.request(id = linkToAllNews, "news")
        //Log.d("adil","a=$a")
        val re = mutableListOf<News>()
        if (a.isNotEmpty()) {
            repeat(a.size) {
                if(it>0){
                    val d = a[it]
                    re.add(News(name = d[0], contenu = d[1], image = d[2],"","","",cat = d[6]))
                }
            }
        }
        return re
    }


    // fin
}