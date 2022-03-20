package com.example.myapplication.groupsPhones

class GPhone(
    var idGPhone:Int,
    var dp:String,
    var region:String,
    var link:String,
    var official:Boolean,
    var fav:Boolean){
    constructor(dp: String="",region: String="",link: String="",official: Boolean=false,fav: Boolean=false) :
            this(0,dp,region, link,official,fav)
}
