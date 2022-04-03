package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.groupsPhones.GPhone
import com.example.myapplication.groupsPhones.GroupsPhoneDao
import com.example.myapplication.phones.Phone
import com.example.myapplication.phones.PhonesDao


@Database(entities = [GPhone::class,Phone::class], version = 1, exportSchema = false)
abstract class MyRoomDB : RoomDatabase() {
    abstract fun myGroupsDao(): GroupsPhoneDao
    abstract fun myPhonesDao(): PhonesDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDB? = null

        fun myDB(
            context: Context,
        ): MyRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDB::class.java,
                    "bigsi-teste-v04"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}