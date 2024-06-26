package com.rezaalamsyah.littlelemon.data.network

import android.content.Context
import com.rezaalamsyah.littlelemon.data.local.LittleLemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository private constructor(
    private val api: AppApi,
    private val appDatabase: LittleLemonDatabase
) {

    companion object {
        private var INSTANCE: AppRepository? = null

        fun getInstance(
            context: Context,
            littleLemonApi: AppApi = AppApi(),
            appDatabase: LittleLemonDatabase = LittleLemonDatabase.getDatabase(context)
        ): AppRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = AppRepository(
                        api = littleLemonApi,
                        appDatabase = appDatabase
                    )
                }
            }
            return INSTANCE!!
        }
    }

    fun getMenuData() = flow {
        var localItems = appDatabase.getMenuItemDao().getMenuItems()
        if (localItems.isEmpty()) {
            val networkItems = api.getMenuData()
            localItems = networkItems.menu.map { it.toDb() }
            appDatabase.getMenuItemDao().insertAll(localItems)
        }
        emit(localItems)
    }.flowOn(Dispatchers.IO)
}