package com.example.myapplication.groupsPhones

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.*

@Composable
fun PageGPh(vm: VmGPh = viewModel()){
    val expandCats  = remember { mutableStateOf(true) }
    val expandOperations  = remember { mutableStateOf(true) }
    val openedMenu  = remember { mutableStateOf(false) }

    MPage(
        contentTitle = {Text("une page")},
        contentActions = {
                         IconButton(onClick = {openedMenu.value = !openedMenu.value}) {
                             Icon(Icons.Filled.MoreVert, contentDescription ="more", tint = Color.White )
                         }
                         MoreMenu(openedMenu =openedMenu , listMenu = listOf(
                             ItemMenu("مجموعة",Icons.Filled.Add,null,null),
                             ItemMenu("تذبير",Icons.Filled.Edit, expandOperations,null),
                             ItemMenu("الكل",Icons.Filled.Delete,null,null),
                         ))
        } ,
        expandCats = expandCats,
        expandOperations =expandOperations,
        contentCats = {
            MCats(vm.mListCats, vm.mListCatsSelected) {
                Log.d("adil", "listSelected=${vm.mListCatsSelected}")
            } },
        contentOperations = { MToggles(vm.listToggle) }
    ) {
        //debut
        val indexExpanded = remember { mutableStateOf(-1) }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(vm.mList) { index, el ->
                MCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Blue)
                        .padding(5.dp),
                    indexExpanded = indexExpanded,
                    index = index,
                    content1 = {
                        MToggle(tint = Color.Red, icon1 = Icons.Filled.Call, onClick = {})
                    },
                    content2 = {
                        Column(Modifier.fillMaxWidth()) {
                            Text(text = el.dp)
                            Text(text = el.region)
                        }
                    },
                    contentSub2 = {
                        Surface(Modifier.fillMaxWidth()) { MToggles(listCToggles = listOf(
                            CToggle(Icons.Filled.Sms,null,false,null,{}),
                            CToggle(Icons.Filled.Place ,null,false,null,{}),
                            CToggle(Icons.Filled.FavoriteBorder,Icons.Filled.Favorite,false,null,{}),
                            )) }
                    },
                    content3 = { MToggles(listCToggles = vm.listToggle3) },
                    weights = arrayOf(1f,6f,1f)
                )
            }
            item { Spacer(Modifier.padding(vertical = 4.dp)) }
        }
        //fin
    }

}