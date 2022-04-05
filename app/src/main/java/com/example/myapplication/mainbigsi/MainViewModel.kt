package com.example.myapplication.mainbigsi

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.database.MyRoomDB
import com.example.myapplication.groupsPhones.GPhone
import com.example.myapplication.groupsPhones.RepoGPhone
import com.example.myapplication.phones.RepoPhone
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val pm: PackageManager = application.packageManager
    private val appContext: Context by lazy {  application.applicationContext }
    private val myDB: MyRoomDB by lazy { MyRoomDB.myDB(application) }

    lateinit var navController: NavHostController
    val mListFavPhone = RepoPhone.mList.filter { it.fav }
    val mListFavGPhone = RepoGPhone.mList.filter { it.fav }

    init {
        viewModelScope.launch {
            RepoPhone.myDao = myDB.myPhonesDao()
            RepoPhone.refreshMList()
        }
        viewModelScope.launch{
            RepoGPhone.myDao = myDB.myGroupsDao()
            RepoGPhone.refreshMList()
        }
    }
        // GPhone dans Favoris
    fun oneFav(gph:GPhone){
        viewModelScope.launch {  RepoGPhone.update(gph) }
        //return  RepoGPhone.mList.size>0 && (RepoGPhone.mList.size == RepoGPhone.mList.filter { it.fav }.size)
    }

}