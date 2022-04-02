package com.example.myapplication.formation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VmFormation:ViewModel() {
    private val mListInitial = mutableListOf<Formation>()
    val mList = RepoFormation.mList

    val mListCats = mutableStateListOf<String>()
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            RepoFormation.refreshMList()
            mListInitial.clear() ; mListInitial.addAll(mList)
            mListCats.clear();mListCats.addAll(RepoFormation.mListCats)
        }
    }

    fun allSel(b:Boolean){
        val t = RepoFormation.mList.toList(); RepoFormation.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.name) }
        }
        RepoFormation.mList.addAll(t)
    }
    fun allFav(){
        val t = RepoFormation.mList.toList(); RepoFormation.mList.clear()
        RepoFormation.mList.clear()
        val nbrFav  = t.filter { it.fav }.size
        val nbrSel = t.filter { it.sel }.size
        t.forEach{
            if(nbrSel > nbrFav) { it.fav = true }
            else if(it.sel) it.fav = !it.fav
        }
        RepoFormation.mList.addAll(t)
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
        val t = RepoFormation.mList.toList(); RepoFormation.mList.clear()
        val v = t[index]
        v.sel = b ; RepoFormation.mList.addAll(t)
        if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(b:Boolean,index:Int):Boolean{
        val t = RepoFormation.mList.toList(); RepoFormation.mList.clear()
        t[index].fav = b ; RepoFormation.mList.addAll(t)
        return  mList.size>0 && (mList.size == mList.filter { it.fav }.size)
    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""
    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
                RepoFormation.mList.forEach {
                if(it.sel) RepoFormation.mList.remove(it) ; Log.d("adil","supprimer ${it.name}")
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
        RepoFormation.mList.clear() ; RepoFormation.mList.addAll(t)
    }

}