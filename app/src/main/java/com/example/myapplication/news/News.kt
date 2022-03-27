package com.example.myapplication.news

class News(
    var idNews:Int,
    var name:String, // titre
    var contenu:String, // region
    var cat:String,
    var image:String,
    var datapub:String,
    var lien: String,
    var type:String,
    var fav:Boolean,
    var sel:Boolean
    ){
    constructor(
         name:String="", // dp
         contenu:String="", // region
         image:String="",
         datapub:String="",
         lien: String="",
         type:String="",
         cat:String="",
         fav:Boolean=false,
         sel:Boolean=false
    ) :
            this(0,name,contenu, image, datapub,lien,type,cat,fav,sel)
}
