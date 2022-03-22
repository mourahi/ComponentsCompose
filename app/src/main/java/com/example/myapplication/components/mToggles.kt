package com.example.myapplication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class CToggle(val icon1: ImageVector, val icon2: ImageVector?, val selected: Boolean? = null, val txt: String?, val onClick: (b: Boolean) -> Unit)

@Composable
fun MToggles(listCToggles: List<CToggle>){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            listCToggles.forEach {
                MToggle(
                    icon1 = it.icon1, icon2 = it.icon2, selected = it.selected ?: false,
                    txt = it.txt
                ) { r ->
                    it.onClick(r)
                }
            }
        }
}


@Composable
fun MToggle(
    modifier:Modifier=Modifier.size(30.dp),
    tint:Color= Color.Black,
    icon1: ImageVector,
    icon2: ImageVector? =null,
    selected: Boolean,
    txt:String?=null,
    onClick:(b:Boolean)->Unit){
    val sel = remember { mutableStateOf(selected)}
    IconButton(onClick = {
            sel.value = !selected
            onClick(sel.value)

    }) {
        Row(verticalAlignment = Alignment.CenterVertically){
            if(txt != null ) Text(text = txt)
            Icon(modifier = modifier,
                imageVector = if (selected && icon2 != null) icon2 else icon1,
                contentDescription = null,
                tint = tint
            )
        }
    }
}