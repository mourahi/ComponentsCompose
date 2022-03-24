package com.example.myapplication.components.mform

import android.util.Log
import androidx.compose.runtime.Composable

@Composable
fun MPageForm(){
    val listCTextFieldForm = listOf(
        CTextFieldForm("","dp"){},
        CTextFieldForm("","region"){},
        CTextFieldForm("click ici","","combo", listCombo = listOf("un","deux","trois")){}
    )
    val listActions = listOf(
        CAction("Save"){
                       listCTextFieldForm.forEach {
                           Log.d("adil","valeur = ${it.value}")
                       }
        },
        CAction("Edit"){},
        CAction("Delete"){}
    )
    MForm(list = listCTextFieldForm,listActions=listActions)
}
