package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.CToggle
import com.example.myapplication.components.MCats
import com.example.myapplication.components.MToggles
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                   val list = listOf("un","deux","trois")
                    val listSelected = mutableListOf("deux")

                    val listToggle = listOf(
                        CToggle(Icons.Filled.FavoriteBorder,
                            Icons.Filled.Favorite,
                            false,
                            "12"){Log.d("adil","I am here 1 click = $it")},
                        CToggle(Icons.Filled.Add,
                            Icons.Filled.Delete,
                            false,
                            null){Log.d("adil","I am here 2 click = $it")},
                    )
                    Column(Modifier.fillMaxSize()){
                        MCats(list, listSelected) {
                            Log.d("adil", "listSelected=$listSelected")
                        }
                        MToggles(listToggle)
                        MCard(
                            content1 = {
                            Icon(Icons.Filled.Call, contentDescription = "", tint = Color.Black)
                        },
                            content2 = {
                                Text(text = "je suis un text")
                            },
                            content3 = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MCard(content1: @Composable() (() -> Unit)? = null,
          content2: @Composable () -> Unit,
          content3: @Composable() (() -> Unit)?
          ){
    Card(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
           if(content1 != null ) Surface( modifier = Modifier
               .weight(1f)
               .border(1.dp, Color.Red) ){
                  content1()
            }
            Surface( modifier = Modifier
                .weight(4f)
                .border(1.dp, Color.Red) ){
                content2()
            }
           if(content3 != null) Surface( modifier = Modifier
               .weight(2f)
               .border(1.dp, Color.Red) ){
                content3()
            }
        }
    }
}


