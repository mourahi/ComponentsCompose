package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VmGPh:ViewModel() {
    private val mListInitial = RepoGPhone.mListInitial
    val mList = RepoGPhone.mList

    val mListCats = RepoGPhone.mListCats
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            Log.d("adil","VmGPh: init")
            RepoGPhone.refreshMList()
          //  mListInitial.clear(); mListInitial.addAll(mList)
           // mListCats.clear(); mListCats.addAll(RepoGPhone.mListCats)
        }
    }

    fun allSel(b:Boolean){
        val t = RepoGPhone.mList.toList(); RepoGPhone.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.name) }
        }
        RepoGPhone.mList.addAll(t)
    }
    fun allFav(){
        val t = RepoGPhone.mList.toList()
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
        if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(gh:GPhone){
       viewModelScope.launch {  RepoGPhone.update(gh) }
        //return  RepoGPhone.mList.size>0 && (RepoGPhone.mList.size == RepoGPhone.mList.filter { it.fav }.size)
    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""
    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
                RepoGPhone.mList.forEach {
                if(it.sel) RepoGPhone.mList.remove(it) ; Log.d("adil","supprimer ${it.name}")
            }
            mListCatsSelected.clear()
        }
    }
    fun getNbrSel():String{
      return  if(mListCatsSelected.size> 0) mListCatsSelected.size.toString() else ""
    }
    fun find(s:String?=null){
        var t =if(mListCatsSelected.isNotEmpty())  mListInitial.filter { it.cat in mListCatsSelected }.toList() else mListInitial.toList()
       t= if(s != null) t.filter { it.name.contains(s,ignoreCase = true) } else t
        RepoGPhone.mList.clear() ; RepoGPhone.mList.addAll(t)
    }

}