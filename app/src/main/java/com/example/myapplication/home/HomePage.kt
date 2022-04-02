package com.example.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.MTab
import com.example.myapplication.groupsPhones.MCardsGPhone
import com.example.myapplication.mainbigsi.viewModelMain
import com.example.myapplication.phones.MCardPhones
import com.example.myapplication.ui.theme.myPadding

@Composable
fun HomePage(){
    val tabIndex = rememberSaveable { mutableStateOf(0) }
    val colla = rememberSaveable { mutableStateOf(-1) }
    val mu = remember { mutableStateOf(false) }
    val selectedFavPhone = remember { mutableStateOf(false)}
    val favFavPhone = remember { mutableStateOf(false)}
    val expandOperationsFavPhone  = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
            Text(text = "BigSI", fontSize = 30.sp)
            MainBox()
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.Black)
                Text(
                    text = "المفضلة", fontSize = 20.sp,
                    modifier = Modifier
                        .padding(myPadding)
                        .fillMaxWidth(), textAlign = TextAlign.Start
                )
            }
            val tabData = listOf(
                arrayOf("المجموعة",Icons.Filled.Workspaces, viewModelMain.mListFavGPhone),
                arrayOf("الهاتف", Icons.Filled.Call, viewModelMain.mListFavPhone) )
            MTab(tabData,tabIndex)

                if (tabIndex.value == 0) {
                    MCardsGPhone(
                        mList = viewModelMain.mListFavGPhone,
                        selected = selectedFavPhone,
                        fav = favFavPhone,
                        expandOperations = expandOperationsFavPhone,
                        oneFav = { _, _ -> false },
                        oneSel = { _, _ -> false }
                    )
                } else {
                       MCardPhones(
                            mList = viewModelMain.mListFavPhone,
                            selected = selectedFavPhone,
                            fav = favFavPhone,
                            expandOperations = expandOperationsFavPhone,
                            oneFav = { _, _ -> false },
                            oneSel = { _, _ -> false }
                        )
                }
    }
}

@Composable
fun FavPhones(){
    Text(text = viewModelMain.mListFavPhone.size.toString())
}

@Composable
fun FavGPhones(){
    Text(text = viewModelMain.mListFavGPhone.size.toString())
}

@Composable
private fun MainFavorisCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = myPadding, start = myPadding, end = myPadding),
        elevation = myPadding
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(myPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "un nom")
            IconButton(onClick = {  }) {
                Icon(Icons.Default.Favorite, contentDescription = "favoris" )
            }
        }
    }
}

@Composable
private fun MainBox() {
    val h = 100.dp
    Column(Modifier.fillMaxWidth()) {
        //GroupsPHone
        Row(
            Modifier
                .fillMaxWidth()
                .padding(myPadding)
        ) {
            Box(
                Modifier
                    .background(Color.Gray)
                    .height(h)
                    .weight(1f)
                    .clickable {
                        viewModelMain.navController.navigate("gphpage")
                    }
            ){
                Text(text = "التواصل", fontSize = 30.sp, modifier = Modifier.align(Alignment.Center), color = Color.White)
                Icon(Icons.Filled.Phone, contentDescription = "", tint = Color.Yellow, modifier = Modifier.size(40.dp))
            }
            //News -------------------------------------------
            Box(
                Modifier
                    .background(Color.Green)
                    .height(h)
                    .weight(1f)
                    .clickable {

                    }
            ){
                Text(text = "المستجدات", fontSize = 30.sp, modifier = Modifier.align(Alignment.Center), color = Color.Red)
                Icon(Icons.Filled.Info, contentDescription = "", tint = Color.Yellow, modifier = Modifier.size(40.dp))
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = myPadding)
                .border(1.dp, Color.Red)
        ) {
            //Formation -------------------------------------------
            Box(
                Modifier
                    .background(Color.Red)
                    .height(h)
                    .weight(1f)
                    .clickable {

                    }
            ){
                Text(text = "التكوينات", fontSize = 20.sp, modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
            //FOrms -------------------------------------------
            Box(
                Modifier
                    .background(Color.Blue)
                    .height(h)
                    .weight(1f)
                    .clickable {

                    }
            ){
                Text(text = "اسثمارات", fontSize = 20.sp, modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        }
    }
}