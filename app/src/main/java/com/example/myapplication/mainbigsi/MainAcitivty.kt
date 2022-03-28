package com.example.myapplication.mainbigsi

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.mform.MPageForm
import com.example.myapplication.gPhServer.PageGPhServer
import com.example.myapplication.groupsPhones.PageGPh
import com.example.myapplication.news.PageNews
import com.example.myapplication.phones.PagePh
import com.example.myapplication.ui.theme.MyApplicationTheme

lateinit var viewModelMain: MainViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    viewModelMain = viewModel()
                    viewModelMain.navController = rememberNavController()
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
                        BigSI()
                    }
                }
            }
        }
    }
}

@Composable
private fun BigSI() {
    NavHost(navController = viewModelMain.navController , startDestination = "mainpage"){
        composable("mainpage"){ PageGPh() }
        composable("mpageform"){ MPageForm() } // a supprimer
        composable("phonepage"){ PagePh() }
        composable("phonepageserver"){ PageGPhServer() }
        composable("pagenews"){ PageNews() }
    }
}
