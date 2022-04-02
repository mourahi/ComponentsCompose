package com.example.myapplication.formation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.myapplication.components.*
import com.example.myapplication.components.mform.CAction
import com.example.myapplication.components.mform.CTextFieldForm
import com.example.myapplication.components.mform.MForm
import com.example.myapplication.mainbigsi.viewModelMain

@SuppressLint("UnrememberedMutableState")
@Composable
fun PageFormation(vm: VmFormation = viewModel()){
    val expandCats  = remember { mutableStateOf(true) }
    val expandOperations  = remember { mutableStateOf(false) }
    val openedMenu  = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(false)}
    val fav = remember { mutableStateOf(false)}
    val openAddGPH = remember { mutableStateOf(false)}

    MPage(
        contentNavigationIcon = {
            IconButton(onClick = { viewModelMain.navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "return")
            }
        } ,
        contentTitle = {
                       MTextField(
                           title = "مستجدات",
                           openEditor = null,
                       ){
                            vm.find(it)
                       }
        },
        contentActions = {
                         IconButton(onClick = {openedMenu.value = !openedMenu.value}) {
                             Icon(Icons.Filled.MoreVert, contentDescription ="more", tint = Color.White )
                         }
                         MoreMenu(openedMenu =openedMenu , listMenu = listOf(
                             ItemMenu(" تحميل",Icons.Outlined.Cloud,null,){
                                 viewModelMain.navController.navigate("phonepageserver")
                             },
                             ItemMenu(" مجموعة", Icons.Filled.Add, null){
                                                 openAddGPH.value = true
                             },
                             ItemMenu(" تدبير",Icons.Filled.Edit, expandOperations,null),
                             ItemMenu(" الكل",Icons.Filled.Delete,null,null),
                         ))
        } ,
        expandCats = expandCats,
        expandOperations =expandOperations,
        contentCats = {
            if(vm.mListCats.size>1) { // no need to show one cat
                MCats(vm.mListCats, vm.mListCatsSelected) {
                    Log.d("adil", "listSelected=${vm.mListCatsSelected}")
                    vm.find()
                } }
        },
        contentOperations = {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                MToggle(
                    icon1 = Icons.Filled.Delete,
                    icon2 = null,
                    selected =false,
                    txt = null
                ){
                    vm.deleteSelected()
                    selected.value = false
                    if(vm.mList.size == 0) expandOperations.value = false
                }
                Spacer(modifier = Modifier.width(10.dp))
                MToggle(
                    icon1 = Icons.Filled.FavoriteBorder,
                    icon2 = Icons.Filled.Favorite,
                    selected = fav.value,
                    txt = vm.getNbrFav()
                ) {
                    if(vm.mListCatsSelected.size > 0) {
                        fav.value = it
                        vm.allFav()
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                MToggle(
                    icon1 = Icons.Filled.CheckBoxOutlineBlank,
                    icon2 = Icons.Filled.CheckBox,
                    selected = if(vm.mList.size > 0) selected.value else false ,
                    txt = vm.getNbrSel()
                ) {
                    selected.value = it
                    vm.allSel(it)
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
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
                            SubcomposeAsyncImage(
                                model = el.image ,
                                loading = {
                                    CircularProgressIndicator()
                                },
                                contentDescription = null
                            )
                        }
                    },
                    content2 = {
                        Column(Modifier.fillMaxWidth()) {
                            Text(text = el.name) // name = dp
                            Text(text = el.contenu)  // region
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                        }
                    },
                    contentSub2 = {
                        Surface(Modifier.fillMaxWidth()) { MToggles(listCToggles = listOf(
                            CToggle(Icons.Filled.Sms,null,null,null) {},
                            CToggle(Icons.Filled.Place ,null,null,null) {},
                            CToggle(Icons.Filled.FavoriteBorder,Icons.Filled.Favorite,el.fav,null) {
                                fav.value = vm.oneFav(it,index)
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
                            selected.value = vm.oneSel(it,index)
                        }
                    } },
                    weights = arrayOf(1f,3f,1f)
                )
            }
            item { Spacer(Modifier.padding(vertical = 4.dp)) }
        }
        //fin
    }

    if(openAddGPH.value) AlertDialog(
        onDismissRequest = { },
        title = {},
        text = {
            val listCTextFieldForm = listOf(
                CTextFieldForm("","dp"){},
                CTextFieldForm("","region"){},
                CTextFieldForm("click ici","","combo", listCombo = listOf("un","deux","trois")){}
            )
            val listActions = listOf(
                CAction("Save"){
                    listCTextFieldForm.forEach {
                        Log.d("adil","valeur = ${it.value}")
                        openAddGPH.value = false
                    }
                },
                CAction("Edit"){},
                CAction("Delete"){}
            )
            MForm(list = listCTextFieldForm,listActions=listActions)
        },
        buttons = {}
            )
}