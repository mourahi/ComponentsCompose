package com.example.myapplication.groupsPhones

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VmGPh:ViewModel() {
    private val mListInitial = mutableListOf<GPhone>()
    val mList = RepoGPhone.mList

    val mListCats = mutableStateListOf<String>()
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            RepoGPhone.refreshMList()
            mListInitial.clear() ; mListInitial.addAll(mList)
            mListCats.clear();mListCats.addAll(RepoGPhone.mListCats)
        }
    }

    fun allSel(b:Boolean){
        val t = RepoGPhone.mList.toList(); RepoGPhone.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.dp) }
        }
        RepoGPhone.mList.addAll(t)
    }
    fun allFav(){
        val t = RepoGPhone.mList.toList(); RepoGPhone.mList.clear()
        RepoGPhone.mList.clear()
        val nbrFav  = t.filter { it.fav }.size
        val nbrSel = t.filter { it.sel }.size
        t.forEach{
            if(nbrSel > nbrFav) { it.fav = true }
            else if(it.sel) it.fav = !it.fav
        }
        RepoGPhone.mList.addAll(t)
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
        val t = RepoGPhone.mList.toList(); RepoGPhone.mList.clear()
        val v = t[index]
        v.sel = b ; RepoGPhone.mList.addAll(t)
        if(v.dp in mListCatsSelected) mListCatsSelected.remove(v.dp) else mListCatsSelected.add(v.dp)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(b:Boolean,index:Int):Boolean{
        val t = RepoGPhone.mList.toList(); RepoGPhone.mList.clear()
        t[index].fav = b ; RepoGPhone.mList.addAll(t)
        return  mList.size>0 && (mList.size == mList.filter { it.fav }.size)
    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""

    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
            mList.filter { it.dp !in mListCatsSelected }
            RepoGPhone.mList.clear()
            RepoGPhone.mList.addAll(mList)
            mListCatsSelected.clear()
        }
    }
    fun getNbrSel():String{
      return  if(mListCatsSelected.size> 0) mListCatsSelected.size.toString() else ""
    }

}