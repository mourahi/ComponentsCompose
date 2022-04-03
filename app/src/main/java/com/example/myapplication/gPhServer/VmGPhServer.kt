package com.example.myapplication.gPhServer

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.groupsPhones.GPhone
import kotlinx.coroutines.launch

class VmGPhServer:ViewModel() {
    private val mListInitial = mutableListOf<GPhone>()
    val mList = RepoGPhoneServer.mList

    val mListCats = mutableStateListOf<String>()
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            RepoGPhoneServer.refreshMList()
            mListInitial.clear() ; mListInitial.addAll(mList)
            mListCats.clear();mListCats.addAll(RepoGPhoneServer.mListCats)
        }
    }

    fun allSel(b:Boolean){
        val t = RepoGPhoneServer.mList.toList(); RepoGPhoneServer.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.name) }
        }
        RepoGPhoneServer.mList.addAll(t)
    }
    fun allFav(){
        val t = RepoGPhoneServer.mList.toList(); RepoGPhoneServer.mList.clear()
        RepoGPhoneServer.mList.clear()
        val nbrFav  = t.filter { it.fav }.size
        val nbrSel = t.filter { it.sel }.size
        t.forEach{
            if(nbrSel > nbrFav) { it.fav = true }
            else if(it.sel) it.fav = !it.fav
        }
        RepoGPhoneServer.mList.addAll(t)
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
        val t = mList.toList(); mList.clear()
        val v = t[index]
        v.sel = b ;
        viewModelScope.launch {  RepoGPhoneServer.insert(gh = v , add= b) }
        mList.addAll(t)
        if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(b:Boolean,index:Int):Boolean{
        val t = RepoGPhoneServer.mList.toList(); RepoGPhoneServer.mList.clear()
        t[index].fav = b ; RepoGPhoneServer.mList.addAll(t)
        return  mList.size>0 && (mList.size == mList.filter { it.fav }.size)
    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""
    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
                RepoGPhoneServer.mList.forEach {
                if(it.sel) RepoGPhoneServer.mList.remove(it) ; Log.d("adil","supprimer ${it.name}")
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
        RepoGPhoneServer.mList.clear() ; RepoGPhoneServer.mList.addAll(t)
    }

}