package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.*
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val vm = MainViewModel()
                    val expandCats  = remember { mutableStateOf(true)}
                    val expandOperations  = remember { mutableStateOf(true)}

                    MPage(
                        expandCats = expandCats,
                        expandOperations =expandOperations,
                        contentCats = {
                            MCats(vm.listCats, vm.listCatsSelected) {
                                Log.d("adil", "listSelected=${vm.listCatsSelected}")
                            } },
                        contentOperations = { MToggles(vm.listToggle) },
                        contentCards = {
                           //debut
                            val indexExpanded = remember { mutableStateOf(-1)}
                            LazyColumn(
                                Modifier.fillMaxSize().padding(horizontal = 5.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                               itemsIndexed(vm.listPhones){ index,el ->
                                   MCard(
                                       modifier = Modifier.fillMaxWidth().border(2.dp, Color.Blue).padding(5.dp),
                                       indexExpanded =  indexExpanded,
                                       index = index,
                                       content1 = {
                                           MToggle(tint= Color.Red , icon1 = Icons.Filled.Call, onClick ={} )
                                       },
                                   content2 = {
                                       Column(Modifier.fillMaxWidth()) {
                                           Text(text = el.name)
                                           Text(text = el.tel)
                                       }
                                   },
                                       contentSub2 = {
                                           Surface(Modifier.fillMaxWidth()){MToggles(listCToggles = vm.listToggle)}
                                       }

                                       ,content3 = { MToggles(listCToggles = vm.listToggle3) }
                                       )
                               }
                                item { Spacer(Modifier.padding(vertical = 4.dp)) }
                            }
                            //fin
                        }
                        )
                }
            }
        }
    }
}
data class CPhone(val name:String,val tel:String)




