package com.example.myapplication.components.mform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MForm(list:List<CTextFieldForm>,listActions:List<CAction>){
    
    LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        items(list){
            when(it.type){
                "combo"-> MComboBoxAlert(it)
                 else -> TextFieldForm(it)
            }
        }
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalArrangement = Arrangement.SpaceAround) {
                listActions.forEach {
                    Button(onClick = { it.action()}) {
                        Text(text = it.label)
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldForm(l: CTextFieldForm){
    val txt = remember { mutableStateOf(l.value)}
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp) ,
        value = txt.value, onValueChange = {  l.value = it; txt.value = it },
        label = { Text(text = l.label)},
        leadingIcon = { IconButton(onClick = { txt.value = "";l.value = "" }) {
            Icon(Icons.Filled.Close,contentDescription = null, tint = Color.Black)
        }}

    )
}

data class CTextFieldForm(var value:String, val label:String,val type:String ="" ,val listCombo:List<String> = listOf() ,val onChange:(s:String)->Unit)
data class CAction(val label:String,val action:()->Unit)



