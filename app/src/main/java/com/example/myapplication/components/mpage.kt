package com.example.myapplication.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MPage(
    contentTitle: @Composable (() -> Unit)? = null,
    contentNavigationIcon:@Composable (() -> Unit)? = null,
    contentActions:@Composable (() -> Unit)? = null,
    expandCats: State<Boolean>,
    expandOperations:State<Boolean>,
    contentCats: @Composable (() -> Unit)? = null,
    contentOperations: @Composable (() -> Unit)? = null,
    contentCards:@Composable (() -> Unit)? = null,
){
    Scaffold (
        topBar={
            TopAppBar(
                title = {
                    if (contentTitle != null)  contentTitle()
                },
                navigationIcon = { if (contentNavigationIcon != null)  contentNavigationIcon()},
                actions = { if(contentActions != null) contentActions()}
            )
        }
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp)){
            AnimatedVisibility (contentCats != null && expandCats.value) {
                Surface(Modifier.fillMaxWidth().padding(5.dp).border(1.dp, Color.Black) ){
                    if (contentCats != null)  contentCats()

                }
            }
            AnimatedVisibility (contentOperations != null && expandOperations.value) {
                Surface(Modifier.fillMaxWidth().padding(5.dp).border(1.dp, Color.Black)) {
                    if (contentOperations != null) contentOperations()
            }
            }
            if (contentCards != null) {
                contentCards()
            }
        }
    }
}