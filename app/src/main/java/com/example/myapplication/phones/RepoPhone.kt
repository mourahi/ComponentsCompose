package com.example.myapplication.phones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet

object RepoPhone{
    private const val linkToAllGPhone = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    lateinit var myDao: PhonesDao
    val mList = mutableStateListOf<Phone>()
    val mListCats = mutableStateListOf<String>()

    suspend fun refreshMList(idGPhone:Int? = null, link:String?=null) {
        if(idGPhone != null){
            myDao.getAll().observeForever {
                updateList(it)
            }
        }else if(link != null && link.length > 5){
            val l  = getDataFromServer()
            updateList(l)
        }
    }

    private fun updateList(l:List<Phone>){
        val d = l ?: listOf()
        val cat = d.map { x -> x.cat }.toSet()
        Log.d("adil", "cat = ${cat.toList()}")
        mList.clear(); mList.addAll(d)
        mListCats.clear(); mListCats.addAll(cat)
    }

    // en cours de préparation
    /*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */
    private suspend fun getDataFromServer(): List<Phone> {
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