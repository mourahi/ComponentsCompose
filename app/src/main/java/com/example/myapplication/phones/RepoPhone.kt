package com.example.myapplication.phones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet
import com.example.myapplication.groupsPhones.GPhone

object RepoPhone{
    private const val linkGPh = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    lateinit var myDao: PhonesDao
    val mList = mutableStateListOf<Phone>()
    val mListCats = mutableStateListOf<String>()
    var activeGPhone: GPhone = GPhone("","")

    suspend fun refreshMList() {
            Log.d("adil","active id=${activeGPhone.idGPhone} link=${activeGPhone.link}")
            if (activeGPhone.idGPhone != null) {
                myDao.getAll().observeForever {
                    updateList(it)
                }
            } else if ( activeGPhone.link.length > 5) {
                val l = getDataFromServer()
                updateList(l)
            }
    }

    private fun updateList(l:List<Phone>){
        val cat = l.map { x -> x.cat }.toSet()
        Log.d("adil", "cat = ${cat.toList()}")
        mList.clear(); mList.addAll(l)
        mListCats.clear(); mListCats.addAll(cat)
    }

    // en cours de préparation
    /*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */
    private suspend fun getDataFromServer(): List<Phone> {
        val a = ApiSheet.request(id = activeGPhone.link, "data")
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

    suspend fun insertPhonesByGPh(add:Boolean=true){
        if(add) {
            val phs = getDataFromServer()
            if(phs.isNotEmpty()){
                val temp = mutableListOf<Phone>()
                phs.forEach {
                    temp.add(
                        Phone(name = it.name, cat = it.cat, person = it.person , refGroup = activeGPhone.idGPhone ?: -1)
                    )
                }
                myDao.insertList(temp)
            }
        }else {
            myDao.delete(activeGPhone.idGPhone?: -1)
        }

    }
}