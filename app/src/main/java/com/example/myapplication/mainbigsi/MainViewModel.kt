package com.example.myapplication.mainbigsi

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavHostController

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val pm: PackageManager = application.packageManager
    private val appContext: Context by lazy {  application.applicationContext }
    lateinit var navController: NavHostController

}