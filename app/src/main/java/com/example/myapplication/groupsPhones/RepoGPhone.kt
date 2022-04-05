package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoGPhone {
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    lateinit var myDao: GroupsPhoneDao

    val mList = mutableStateListOf<GPhone>()
    val mListInitial = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList() {
        myDao.getAll().observeForever {
            updateList(it)
        }

    }

    private fun updateList(l:List<GPhone>){
        mList.clear();mListCats.clear()
        val cat = l.map { x -> x.cat }.toSet()
        Log.d("adil", "cat = ${cat.toList()}")
         mList.addAll(l); mListCats.addAll(cat)
        mListInitial.clear();mListInitial.addAll(l)
    }

    suspend fun update(gh:GPhone){
        myDao.update(gh)
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