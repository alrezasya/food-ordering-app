package com.rezaalamsyah.littlelemon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rezaalamsyah.littlelemon.data.model.MenuItemDb

@Database(entities = [MenuItemDb::class], version = 1)
abstract class LittleLemonDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: LittleLemonDatabase? = null
        private const val DATABASE_NAME = "little_lemon_db"

        fun getDatabase(context: Context): LittleLemonDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, LittleLemonDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getMenuItemDao(): MenuItemDao
}