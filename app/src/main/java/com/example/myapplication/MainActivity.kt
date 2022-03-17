package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.components.*
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
                        ListCard(
                            listOf(
                            Phone("adil","1111111"),
                            Phone("saida","22222")
                        ),
                            listOf(
                                CToggle(Icons.Filled.Check,
                                    Icons.Filled.CheckCircle,
                                    false,
                                    null){Log.d("adil","I am here 1 click = $it")})
                            )
                    }
                }
            }
        }
    }
}
data class Phone(val name:String,val tel:String)



@Composable
fun ListCard(list: List<Phone>,listToggle:List<CToggle>,
             onClickContent1:(item:Phone)->Unit={},
             onClickContent2:(item:Phone)->Unit={}
             ){
    LazyColumn(Modifier.fillMaxSize()){
        items(list){ el->
            MCard(
                content1 = {
                    MCardContent1(ic = Icons.Filled.Call, onClick ={
                        onClickContent1(el)
                    } )
                },
                content2 = {
                    MCardContent2(title = el.name,el.tel) {onClickContent2(el)}
                },
                content3 = null /*{ MToggles(listCToggles = listToggle) }*/,
                weights = arrayOf(1f,5f,1f)
            )
        }
    }
}



