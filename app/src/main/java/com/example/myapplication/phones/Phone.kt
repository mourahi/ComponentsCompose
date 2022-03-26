package com.example.myapplication.phones
/*cycle	commune	gresa	ecole	nom
tel	fonction	email	geo	role */

class Phone(
    var idPhone: Int,
    var name: String, // ecole 3
    var cat: String, // cycle 0
    var subcat: String, //commune 1
    var gresa: String, //2
    var tel: String, //5
    var person: String, //nom 4
    var fonction: String, // 6
    var email: String, //7
    var geo: String, // 8
    var refGroup: Int,
    var fav: Boolean,
    var sel: Boolean,
    var role:String, //9
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
