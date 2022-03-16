package com.example.myapplication.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MCard(content1: @Composable() (() -> Unit)? = null,
          content2: @Composable () -> Unit,
          content3: @Composable() (() -> Unit)?,
          weights: Array<Float>? = null
){
    Card(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if(content1 != null ) Surface( modifier = Modifier
                .weight(if(weights != null) weights[0] else 1f)
                .border(1.dp, Color.Red) ){
                content1()
            }
            Surface( modifier = Modifier
                .weight(if(weights != null) weights[1] else 4f)
                .border(1.dp, Color.Red) ){
                content2()
            }
            if(content3 != null ) Surface( modifier = Modifier
                .weight(if(weights != null) weights[2] else 1f)
                .border(1.dp, Color.Red) ){
                content3()
            }
        }
    }
}