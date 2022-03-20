package com.example.myapplication.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MCard(
    modifier: Modifier,
    indexExpanded: MutableState<Int>,
    index:Int,
    content1: @Composable (() -> Unit)? = null,
    content2: @Composable () -> Unit,
    contentSub2:@Composable (() -> Unit)? = null,
    content3: @Composable (() -> Unit)? = null,
    weights: Array<Float>? = null
){
    Card(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            if(content1 != null ) Surface( modifier = Modifier
                .weight(if (weights != null) weights[0] else 1f)
                .border(1.dp, Color.Red) ){
                content1()
            }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(if (weights != null) weights[1] else 4f)
                        .border(1.dp, Color.Red))
                    {
                        Surface(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    indexExpanded.value = if(indexExpanded.value == index) -1 else index

                                }){ content2() }
                    AnimatedVisibility (
                        visible = contentSub2 != null  &&  indexExpanded.value == index,
                        enter = expandVertically(),

                    ) {
                        if (contentSub2 != null) {
                            contentSub2()
                        }
                    }
                }

            if(content3 != null ) Surface( modifier = Modifier
                .weight(if (weights != null) weights[2] else 1f)
                .border(1.dp, Color.Red) ){
                content3()
            }
        }
    }
}

@Composable
fun MCardContent2(title:String,subTitle:String?){


}

@Composable
fun MCardContent1(modifier:Modifier=Modifier, tint:Color= Color.Black, ic:ImageVector,onClick: (b:Boolean) -> Unit){
    MToggle(modifier,tint,icon1 = ic, onClick = {
        onClick(it)
    })
}