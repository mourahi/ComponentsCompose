package com.example.myapplication.phones

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VmPh:ViewModel() {
    private val mListInitial = RepoPhone.mListInitial
    val mList = RepoPhone.mList

    val mListCats = RepoPhone.mListCats
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            Log.d("adil","VmPh: init")
            RepoPhone.refreshMList()
            if(RepoPhone.mListInitial.size>0){
                RepoPhone.mListInitial.forEach {
                    it.sel = false
                }
            }
        }
    }

    fun allSel(b:Boolean){
        val t = RepoPhone.mList.toList(); RepoPhone.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.name) }
        }
        RepoPhone.mList.addAll(t)
    }
    fun allFav(b:Boolean){
        val r = mList.filter { it.sel }.map { it.idPhone }
        viewModelScope.launch { RepoPhone.updateListFav(r as List<Int>,b) }
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
       RepoPhone.mList.forEachIndexed { i, phone ->
            if(i == index) phone.sel = b
        }
        //if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mList.size == mList.filter { it.fav }.size
    }
    fun oneFav(ph:Phone){
       viewModelScope.launch { RepoPhone.update(ph) }

    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""
    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
                RepoPhone.mList.forEach {
                if(it.sel) RepoPhone.mList.remove(it) ; Log.d("adil","supprimer ${it.name}")
            }
            mListCatsSelected.clear()
        }
    }
    fun getNbrSel():String{
      return  if(mListCatsSelected.size> 0) mListCatsSelected.size.toString() else ""
    }
    fun find(s:String?=null){
       viewModelScope.launch {
               RepoPhone.find(s ?: "",mListCatsSelected)
       }
    }

}