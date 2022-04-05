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
            mListInitial.clear()
            RepoPhone.refreshMList()
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
    fun allFav(){
        val t = RepoPhone.mList.toList(); RepoPhone.mList.clear()
        RepoPhone.mList.clear()
        val nbrFav  = t.filter { it.fav }.size
        val nbrSel = t.filter { it.sel }.size
        t.forEach{
            if(nbrSel > nbrFav) { it.fav = true }
            else if(it.sel) it.fav = !it.fav
        }
        RepoPhone.mList.addAll(t)
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
        val t = RepoPhone.mList.toList(); RepoPhone.mList.clear()
        val v = t[index]
        v.sel = b ; RepoPhone.mList.addAll(t)
        if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(b:Boolean,index:Int):Boolean{
        val t = RepoPhone.mList.toList(); RepoPhone.mList.clear()
        t[index].fav = b ; RepoPhone.mList.addAll(t)
        return  mList.size>0 && (mList.size == mList.filter { it.fav }.size)
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
        Log.d("adil","mListSelected = ${mListCatsSelected.toList()} mListInitial = $mListInitial")
        var t =if(mListCatsSelected.isNotEmpty())  mListInitial.filter { it.cat in mListCatsSelected }.toList() else mListInitial.toList()
       t= if(s != null) t.filter { it.name.contains(s,ignoreCase = true) } else t
        RepoPhone.mList.clear() ; RepoPhone.mList.addAll(t)
    }

}