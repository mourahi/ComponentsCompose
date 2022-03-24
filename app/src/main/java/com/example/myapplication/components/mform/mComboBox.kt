package com.example.myapplication.components.mform

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MListSelect(l:List<String>, opened: MutableState<Boolean>, onSelect:(s:String)->Unit){
    LazyColumn(
        Modifier
            .padding(5.dp))
    {
        items(l)
        {
            Text(
                text = it,
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .border(1.dp, Color.Black)
                    .padding(10.dp)
                    .clickable {
                        onSelect(it)
                        opened.value = false
                    })
        }
    }
}

@Composable
fun MComboBoxAlert(l: CTextFieldForm){
    val opened= remember { mutableStateOf(false) }
    val txt = remember { mutableStateOf("Click ici") }
    if(txt.value.isEmpty()) txt.value = "click ici"
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .border(1.dp, Color.Gray)
            .padding(15.dp)
            .clickable { opened.value = !opened.value }){
        Icon(imageVector =Icons.Filled.ArrowDropDown , contentDescription =null, tint = Color.Black )
        Text(text = txt.value)
    }
    if(opened.value) AlertDialog(
        onDismissRequest = { opened.value = false },
        buttons = {},
        text = {  MListSelect(l = l.listCombo, opened = opened, onSelect ={
            txt.value = it
            l.value = it
        } )  }

    )
}