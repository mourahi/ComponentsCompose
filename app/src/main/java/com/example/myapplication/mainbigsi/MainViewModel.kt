package com.example.myapplication.mainbigsi

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.database.MyRoomDB
import com.example.myapplication.groupsPhones.GPhone
import com.example.myapplication.groupsPhones.RepoGPhone
import com.example.myapplication.phones.Phone
import com.example.myapplication.phones.RepoPhone
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val pm: PackageManager = application.packageManager
    private val appContext: Context by lazy {  application.applicationContext }
    private val myDB: MyRoomDB by lazy { MyRoomDB.myDB(application) }

    lateinit var navController: NavHostController
    val mListFavPhone = RepoPhone.mListFav
    var mListFavGPhone = RepoGPhone.mList

    init {
        Log.d("adil","initial MainViewModel")
        viewModelScope.launch {
            RepoPhone.myDao = myDB.myPhonesDao()
            Log.d("adil","mainvviewmodel1")
            RepoPhone.refreshMList(true)
            Log.d("adil","Mainviemodel1: mlist = $mListFavGPhone")
        }
        viewModelScope.launch{
            RepoGPhone.myDao = myDB.myGroupsDao()
            Log.d("adil","mainvviewmodel2")
            RepoGPhone.refreshMList()
        }
    }
        // GPhone dans Favoris
    fun oneFav(gph:GPhone){
        viewModelScope.launch {  RepoGPhone.update(gph) }
        //return  RepoGPhone.mList.size>0 && (RepoGPhone.mList.size == RepoGPhone.mList.filter { it.fav }.size)
    }
    fun oneFavPhone(ph:Phone){
        viewModelScope.launch {
            RepoPhone.update(ph)
           // RepoPhone.refreshMList(true)
        }
    }

}