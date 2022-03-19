package com.example.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MPage(
    expandCats: State<Boolean>,
    expandOperations:State<Boolean>,
    contentCats: @Composable (() -> Unit)? = null,
    contentOperations: @Composable (() -> Unit)? = null,
    contentCards:@Composable (() -> Unit)? = null,
){
    Column(Modifier.fillMaxSize().padding(5.dp)){
        if (contentCats != null && expandCats.value) {
            contentCats()
        }
        if (contentOperations != null && expandOperations.value) {
            contentOperations()
        }
        if (contentCards != null) {
            contentCards()
        }
    }

}