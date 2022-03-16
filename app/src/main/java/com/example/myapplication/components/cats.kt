package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MCats(list: List<String>,
          listSelected:MutableList<String>,
          onChange:()->Unit)
{
    val raz= remember { mutableStateOf(false) }
    LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
        item {
            IconButton(onClick = {
                raz.value = true
                listSelected.clear()
                onChange()
            }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "clear", tint = Color.Black )
            }
        }
        items(list){ txt->
            MCat(txt, txt in listSelected,raz){ b->
                if(raz.value) raz.value = false
                if(b) listSelected.add(txt) else listSelected.remove(txt)
                onChange()
            }
        }
    }
}

@Composable
private fun MCat(txt:String, selected:Boolean=false, raz: MutableState<Boolean>, onClick:(b:Boolean)->Unit){
    val sel= remember { mutableStateOf(selected) }
    if(raz.value) sel.value = false
    Text(txt , modifier = Modifier
        .padding(2.dp)
        .border(1.dp, Color.Red)
        .background(if (sel.value) Color.Gray else Color.White)
        .clickable {
            sel.value = !sel.value
            onClick(sel.value)
        })
}