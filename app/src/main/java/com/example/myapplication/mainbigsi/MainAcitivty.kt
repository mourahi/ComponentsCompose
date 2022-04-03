package com.example.myapplication.mainbigsi

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.mform.MPageForm
import com.example.myapplication.formation.PageFormation
import com.example.myapplication.gPhServer.PageGPhServer
import com.example.myapplication.groupsPhones.PageGPh
import com.example.myapplication.home.HomePage
import com.example.myapplication.news.PageNews
import com.example.myapplication.phones.PagePh
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
                        BigSI(application)
                    }
                }
            }
        }
    }
}

@Composable
private fun BigSI(application: Application) {
    val viewModelMain = MainViewModel(application)
    viewModelMain.navController = rememberNavController()
    NavHost(navController = viewModelMain.navController , startDestination = "homepage"){
        composable("homepage"){ HomePage(viewModelMain) }
        composable("gphpage"){ PageGPh(viewModelMain = viewModelMain) }
        composable("mpageform"){ MPageForm() } // a supprimer
        composable("phonepage"){ PagePh(viewModelMain = viewModelMain) }
        composable("phonepageserver"){ PageGPhServer(viewModelMain = viewModelMain) }
        composable("pagenews"){ PageNews(viewModelMain = viewModelMain) }
        composable("pageformation"){ PageFormation(viewModelMain = viewModelMain) }
    }
}


