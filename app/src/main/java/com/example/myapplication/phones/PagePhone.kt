package com.example.myapplication.phones

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.*
import com.example.myapplication.components.mform.CAction
import com.example.myapplication.components.mform.CTextFieldForm
import com.example.myapplication.components.mform.MForm
import com.example.myapplication.mainbigsi.MainViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun PagePh(vm: VmPh = viewModel(),viewModelMain: MainViewModel){
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
        },
        contentTitle = {
                       MTextField(
                           title = RepoPhone.activeGPhone.name,
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
                             ItemMenu(" تحميل",Icons.Outlined.Cloud,null,null),
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
            Log.d("adil","cat depuis pagehone = ${vm.mListCats.toList()}")
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
            MCardPhones(mList = vm.mList,selected,fav,expandOperations,
                oneFav = { it,index-> vm.oneFav(it,index) },
            oneSel = { it,index -> vm.oneSel(it,index) })
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

