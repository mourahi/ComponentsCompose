package com.example.myapplication.news

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VmNews:ViewModel() {
    private val mListInitial = mutableListOf<News>()
    val mList = RepoNews.mList

    val mListCats = mutableStateListOf<String>()
    val mListCatsSelected = mutableListOf<String>()
    init {
        viewModelScope.launch {
            RepoNews.refreshMList()
            mListInitial.clear() ; mListInitial.addAll(mList)
            mListCats.clear();mListCats.addAll(RepoNews.mListCats)
        }
    }

    fun allSel(b:Boolean){
        val t = RepoNews.mList.toList(); RepoNews.mList.clear()
        t.forEach{
            it.sel = b
        }
        mListCatsSelected.clear()
        if(b){
            t.forEach { r-> mListCatsSelected.add(r.name) }
        }
        RepoNews.mList.addAll(t)
    }
    fun allFav(){
        val t = RepoNews.mList.toList(); RepoNews.mList.clear()
        RepoNews.mList.clear()
        val nbrFav  = t.filter { it.fav }.size
        val nbrSel = t.filter { it.sel }.size
        t.forEach{
            if(nbrSel > nbrFav) { it.fav = true }
            else if(it.sel) it.fav = !it.fav
        }
        RepoNews.mList.addAll(t)
    }
    fun oneSel(b:Boolean,index:Int): Boolean{
        val t = RepoNews.mList.toList(); RepoNews.mList.clear()
        val v = t[index]
        v.sel = b ; RepoNews.mList.addAll(t)
        if(v.name in mListCatsSelected) mListCatsSelected.remove(v.name) else mListCatsSelected.add(v.name)
        return  mListCatsSelected.size == mList.size && mListCatsSelected.size>0
    }
    fun oneFav(b:Boolean,index:Int):Boolean{
        val t = RepoNews.mList.toList(); RepoNews.mList.clear()
        t[index].fav = b ; RepoNews.mList.addAll(t)
        return  mList.size>0 && (mList.size == mList.filter { it.fav }.size)
    }
    fun getNbrFav():String{
        val nbrF = mList.filter { it.fav }.size
       return if(nbrF>0)  nbrF.toString() else ""
    }
    fun deleteSelected(){
        if(mListCatsSelected.isNotEmpty()){
                RepoNews.mList.forEach {
                if(it.sel) RepoNews.mList.remove(it) ; Log.d("adil","supprimer ${it.name}")
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
        RepoNews.mList.clear() ; RepoNews.mList.addAll(t)
    }

}