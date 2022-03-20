package com.example.myapplication.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MCats(list: List<String>,
          listSelected:MutableList<String>,
          onChange:()->Unit)
{
    val raz= remember { mutableStateOf(false) }
    val nbrSelected = remember { mutableStateOf(listSelected.size)}
    Row(Modifier.fillMaxWidth()){
        IconButton(onClick = {
            raz.value = true
            listSelected.clear()
            nbrSelected.value = 0
            onChange()
        }) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear",
                    tint = Color.Black
                )
                if(nbrSelected.value > 0)   Text(text = nbrSelected.value.toString())
            }
        }
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            reverseLayout = false
        )
        {
            items(list) { txt ->
                MCat(txt, txt in listSelected, raz) { b ->
                    if (raz.value) raz.value = false
                    if (b) listSelected.add(txt) else listSelected.remove(txt)
                    nbrSelected.value = listSelected.size
                    onChange()
                }
            }
        }
    }
}

@Composable
private fun MCat(txt:String, selected:Boolean=false, raz: MutableState<Boolean>, onClick:(b:Boolean)->Unit){
    val sel= remember { mutableStateOf(selected) }
    if(raz.value) sel.value = false
    Surface(
        shape = RoundedCornerShape(16.dp),
        color =if (sel.value) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
        modifier = Modifier
            .border(1.dp, Color.Yellow)
            .clickable
            {
                sel.value = !sel.value
                onClick(sel.value)
            }
        )
    {
        Text(txt , modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .defaultMinSize(45.dp),textAlign = TextAlign.Center
            , fontSize =MaterialTheme.typography.h5.fontSize)
    }
}