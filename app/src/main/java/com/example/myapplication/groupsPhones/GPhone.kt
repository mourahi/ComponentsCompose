package com.example.myapplication.groupsPhones

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "groups_phone")
class GPhone(
    @PrimaryKey(autoGenerate = true)  var idGPhone:Int?,
    @ColumnInfo var name:String, // dp nom
    @ColumnInfo var cat:String, // region
    @ColumnInfo var link:String,
    @ColumnInfo var official:Boolean,
    @ColumnInfo var fav:Boolean,
    var sel:Boolean
    ){
    constructor(name: String="",cat: String="",link: String="",official: Boolean=false,fav: Boolean=false,sel:Boolean=false) :
            this(null,name,cat, link,official,fav,sel)
}

@Dao
interface GroupsPhoneDao {
    @Query("SELECT * FROM groups_phone")
    fun getAll(): LiveData<List<GPhone>>

    @Insert
    suspend fun insert(gh:GPhone):Long

    @Query("DELETE FROM groups_phone WHERE link = :link")
    suspend fun delete(link:String)
}