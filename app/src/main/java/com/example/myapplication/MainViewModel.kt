package com.example.myapplication

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.components.CToggle

class MainViewModel:ViewModel() {
    val listCats = listOf("un","deux","trois")
    val listCatsSelected = mutableListOf("deux")

    val listPhones = listOf(
        CPhone("adil", "1111111"),
        CPhone("saida", "22222")
    )

    val listToggle = listOf(
        CToggle(icon1 = Icons.Filled.FavoriteBorder,
            icon2 = Icons.Filled.Favorite,
            selected = false,
            txt = "12"){ Log.d("adil","I am here 1 click = $it")},
        CToggle(
            icon1 = Icons.Filled.Add,
            icon2 = Icons.Filled.Delete,
            selected =  false,
            txt = null){ Log.d("adil","I am here 2 click = $it")},
    )

    val listToggle3 = listOf(
        CToggle(
            icon1 = Icons.Filled.Check,
            icon2 = Icons.Filled.CheckCircle,
            selected = false,
            txt =  null){Log.d("adil","I am here 1 click = $it")})
}