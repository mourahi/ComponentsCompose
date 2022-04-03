package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.CToggle
import com.example.myapplication.components.MCard
import com.example.myapplication.components.MToggle
import com.example.myapplication.components.MToggles
import com.example.myapplication.mainbigsi.MainViewModel

@Composable
fun MCardsGPhone(mList:List<GPhone>, selected: MutableState<Boolean>,
                 fav: MutableState<Boolean>,
                 expandOperations: MutableState<Boolean>,
                 oneFav:(Boolean,Int)->Boolean,
                 oneSel:(Boolean,Int)->Boolean){
    //debut
    val indexExpanded = remember { mutableStateOf(-1) }
    val viewModelMain:MainViewModel = viewModel()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        itemsIndexed(mList) { index, el ->
            Log.d("adil","ici = el.sel = ${el.sel}")
            MCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Blue)
                    .padding(5.dp),
                indexExpanded = indexExpanded,
                index = index,
                content1 = {
                    Box {
                        if(el.fav)   Icon(modifier = Modifier.align(Alignment.TopStart) ,imageVector = Icons.Filled.Favorite, tint = Color.Green, contentDescription = null)
                        MToggle(
                            tint = Color.Red,
                            icon1 = Icons.Filled.Call,
                            selected = false
                        ) {
                            viewModelMain.navController.navigate("phonepage")
                        }
                    }
                },
                content2 = {
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = el.name) // name = dp
                        Text(text = el.cat)  // region
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))
                    }
                },
                contentSub2 = {
                    Surface(Modifier.fillMaxWidth()) { MToggles(listCToggles = listOf(
                        CToggle(Icons.Filled.Sms,null,null,null) {},
                        CToggle(Icons.Filled.Place ,null,null,null) {},
                        CToggle(Icons.Filled.FavoriteBorder, Icons.Filled.Favorite,el.fav,null) {
                            fav.value = oneFav(it,index)
                        },
                    )) }
                },
                content3 =if(!expandOperations.value) null else  {  {
                    MToggle(
                        icon1 = Icons.Filled.CheckBoxOutlineBlank,
                        icon2 = Icons.Filled.CheckBox,
                        selected = el.sel,
                        txt = null
                    ) {
                        selected.value = oneSel(it,index)
                    }
                } },
                weights = arrayOf(1f,6f,1f)
            )
        }
        item { Spacer(Modifier.padding(vertical = 4.dp)) }
    }
    //fin
}