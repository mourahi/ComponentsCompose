package com.example.myapplication.phones

import androidx.lifecycle.LiveData
import androidx.room.*

/*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */

@Entity(tableName = "phones")
class Phone(
    @PrimaryKey(autoGenerate = true) var idPhone: Int,
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
                0,
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
    @Query("SELECT * FROM phones")
    fun getAll(): LiveData<List<Phone>>
}