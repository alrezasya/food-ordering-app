package com.rezaalamsyah.littlelemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rezaalamsyah.littlelemon.data.model.MenuItemDb

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MenuItemDb>)

    @Query("SELECT * FROM menu_items")
    suspend fun getMenuItems(): List<MenuItemDb>

}