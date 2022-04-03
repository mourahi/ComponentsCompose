package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoGPhone {
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    lateinit var myDao: GroupsPhoneDao

    val mList = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    fun refreshMList() {
        myDao.getAll().observeForever {
            val d = it ?: listOf()
            val cat = d.map { x -> x.cat }.toSet()
            Log.d("adil", "cat = ${cat.toList()}")
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)
        }
    }

    // en cours de préparation
    private suspend fun getDataFromServer(): List<GPhone> {
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