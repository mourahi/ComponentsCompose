package com.example.myapplication.groupsPhones

class GPhone(
    var idGPhone:Int,
    var name:String, // dp
    var cat:String, // region
    var link:String,
    var official:Boolean,
    var fav:Boolean,
    var sel:Boolean
    ){
    constructor(dp: String="",region: String="",link: String="",official: Boolean=false,fav: Boolean=false,sel:Boolean=false) :
            this(0,dp,region, link,official,fav,sel)
}
