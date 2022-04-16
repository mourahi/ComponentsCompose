package com.example.myapplication.phones

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.database.ApiSheet
import com.example.myapplication.groupsPhones.GPhone

object RepoPhone{
    //private const val linkGPh = "1__YWeJR26tpyCep99NETXyMi9lXe1MA3JiJWr4y2-n0"
    lateinit var myDao: PhonesDao
    val mList = mutableStateListOf<Phone>()
    val mListFav = mutableStateListOf<Phone>()
    val mListInitial = mutableStateListOf<Phone>()
    val mListCats = mutableStateListOf<String>()
    var activeGPhone: GPhone = GPhone("","")

    suspend fun refreshMList(fav:Boolean = false) {
            Log.d("adil","RepoPhone: refreshMList id=${activeGPhone.idGPhone} link=${activeGPhone.link}")

        val i = activeGPhone.idGPhone
        when {
            fav -> {
                myDao.getAllFav().collect{
                    Log.d("adil","RepoPhone:refreshi collect fav")
                    mListFav.clear();mListFav.addAll(it)
                }
            }
            i != null  -> {
                myDao.getAll(i).collect{
                    Log.d("adil","RepoPhone: refreshi collect id != null")
                    createList(it)
                }

            }
            activeGPhone.link.length > 5 -> {
                Log.d("adil","RepoPhone: refreshi fromserver")
                val l = getDataFromServer()
                createList(l)
            }

        }
    }

    private fun createList(l:List<Phone>){
        val cat = l.map { x -> x.cat }.toSet()
        Log.d("adil","RepoPhone: cat = $cat")
        mList.clear(); mListCats.clear()
        mListCats.addAll(cat) ; mList.addAll(l)
        mListInitial.clear() ; mListInitial.addAll(l)
    }
    suspend fun update(ph:Phone){
        myDao.update(ph)
    }
    suspend fun updateListFav(l:List<Int>,b:Boolean){
        Log.d("adil","essai update de ${l.size}")
        myDao.updateListFav(l,b)
    }
    suspend fun find(s:String, mListSelected: List<String>? = null){
     if(s =="") {
         activeGPhone.idGPhone?.let {
             myDao.getAll(it).collect { x->
              val r= if(mListSelected != null)  x.filter { v-> v.cat in mListSelected } else x
                 mList.clear(); mList.addAll(r)
             }
         }
     }
        else activeGPhone.idGPhone?.let {
         myDao.find(it, s).collect{ x->
             val r= if(mListSelected != null)  x.filter { v-> v.cat in mListSelected } else x
             mList.clear(); mList.addAll(r)
         }
     }
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