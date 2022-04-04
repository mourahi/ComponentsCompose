package com.example.myapplication.gPhServer

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet
import com.example.myapplication.groupsPhones.GPhone
import com.example.myapplication.groupsPhones.RepoGPhone
import com.example.myapplication.phones.RepoPhone

object RepoGPhoneServer{
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    val mList = mutableStateListOf<GPhone>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(){
        val d = groupsPhoneFromServer()
            val cat = d.map{it.cat}.toSet()
        val temp = RepoGPhone.myDao.getAll().observeForever {
            val existant = it.map { x->x.link }
            Log.d("adil", "exisitant =${existant.toList()}")
            d.forEach { xx->
                xx.sel = xx.link in existant
            }
            mList.clear(); mList.addAll(d)
            mListCats.clear(); mListCats.addAll(cat)
        }

    }

    // en cours de préparation
    private suspend fun groupsPhoneFromServer(): List<GPhone> {
        val a = ApiSheet.request(id = linkToAllGPhone, "groupe")
        //Log.d("adil","a=$a")
        val re = mutableListOf<GPhone>()
        if (a.isNotEmpty()) {
            repeat(a.size) {
                val d = a[it]
                re.add(GPhone(name = d[0], cat = d[1], link = d[2], official = true))
            }
        }
        return re
    }
    //update ROOM
    suspend fun insert(gh:GPhone,add:Boolean){
            if(add) {
                val t = GPhone(name = gh.name,cat=gh.cat,link=gh.link, official = true)
                Log.d("adil","idGPHONEDeprat  = ${gh.idGPhone}")
                val i = RepoGPhone.myDao.insert(t).toInt()
                gh.idGPhone = i
                Log.d("adil","idGPHONEFin  = ${gh.idGPhone}")
       }else {
           RepoGPhone.myDao.delete(gh.link)
       }
        RepoPhone.activeGPhone = gh
        RepoPhone.insertPhonesByGPh(add)
    }
}