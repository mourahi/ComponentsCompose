package com.example.myapplication.phones

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */

@Entity(tableName = "phones")
class Phone(
    @PrimaryKey(autoGenerate = true) @ColumnInfo var idPhone: Int?,
    @ColumnInfo var name: String, // ecole 3
    @ColumnInfo var cat: String, // cycle 0
    @ColumnInfo var subcat: String, //commune 1
    @ColumnInfo var gresa: String, //2
    @ColumnInfo var tel: String, //5
    @ColumnInfo var person: String, //nom 4
    @ColumnInfo var fonction: String, // 6
    @ColumnInfo var email: String, //7
    @ColumnInfo var geo: String, // 8
    @ColumnInfo var refGroup: Int,
    @ColumnInfo var fav: Boolean,
    var sel: Boolean,
    @ColumnInfo var role:String, //9
) {
    constructor(
        name: String = "", // ecole
        cat: String = "", // cycle
        subcat: String = "", //commune
        gresa: String = "",
        tel: String = "",
        person: String = "",
        fonction: String = "",
        email: String = "",
        geo: String = "",
        refGroup: Int = -1,
        fav: Boolean = false,
        sel: Boolean = false,
        role: String = ""

    ) :
            this(
                null,
                name, // ecole
                cat, // cycle
                subcat, //commune
                gresa,
                tel,
                person,
                fonction,
                email,
                geo,
                refGroup,
                fav,
                sel,
                role
            )
}

@Dao
interface PhonesDao {
    @Query("SELECT * FROM phones WHERE refGroup = :idGPhone" )
    fun getAll(idGPhone: Int): Flow<List<Phone>>

    @Query("SELECT * FROM phones WHERE fav = 1 ")
    fun getAllFav():Flow<List<Phone>>

    @Insert
    suspend fun insertList(phs:List<Phone>)

    @Query("DELETE FROM phones WHERE refGroup = :refGroup")
    suspend fun delete(refGroup:Int)

    @Update
    suspend fun update(ph:Phone)
}